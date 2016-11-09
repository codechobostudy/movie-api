package io.codechobo._testhelper;

import org.apache.commons.lang3.time.DateUtils;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.CustomBigDecimalComparator;
import org.javers.core.diff.custom.CustomPropertyComparator;
import org.javers.core.metamodel.clazz.EntityDefinitionBuilder;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;
import org.junit.Assert;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.not;

/**
 * 단위 테스트용 Assertion 유틸리티
 */
public class AssertUtils {

    private static final ThreadLocal<Map<Class<?>, Javers>> JAVERS_THREAD_LOCAL = new ThreadLocal<Map<Class<?>, Javers>>() {
        @Override
        protected Map<Class<?>, Javers> initialValue() {
            return new ConcurrentHashMap<>(10);
        }
    };

    private static Javers getJavers(final Class<?> clazz) {
        final Map<Class<?>, Javers> map = JAVERS_THREAD_LOCAL.get();
        return map.computeIfAbsent(clazz, c -> createJavers(c));
    }

    private static Javers createJavers(final Class<?> clazz) {
        final JaversBuilder builder = JaversBuilder.javers();
        final EntityDefinitionBuilder entityBuilder = EntityDefinitionBuilder.entityDefinition(clazz);
        final List<String> ignores = new ArrayList<>(5);

        for (final Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                if (field.isAnnotationPresent(Version.class)) {
                    ignores.add(field.getName());
                }
            }
            if (!ignores.isEmpty()) {
                entityBuilder.withIgnoredProperties(ignores);
            }
        }
        builder.registerEntity(entityBuilder.build());
        builder.registerCustomComparator(new CustomBigDecimalComparator(10), BigDecimal.class);
        builder.registerCustomComparator(new CustomDateComparator(), Date.class);
        return builder.build();
    }

    /**
     * 지정된 두개의 인스턴스를 비교하여 다를 경우 Assertion을 발생 시킨다.
     *
     * @param actual   검증할 대상
     * @param expected 기대하는 값
     * @param <T>      검증할 대상의 타입
     */

    public static <T> void assertEquals(final T actual, final T expected) {
        final Diff diff = diff(expected, actual);
        Assert.assertThat(diff.toString(), diff.hasChanges(), not(true));
    }

    /**
     * 지정된 두개의 인스턴스를 비교하여 비교 결과 반환.
     *
     * @param actual   비교할 대상
     * @param expected 기대하는 값
     * @param <T>      비교대상의 타입
     * @return 비교 결과
     */
    public static <T> Diff diff(final T actual, final T expected) {
        return getJavers(expected.getClass()).compare(expected, actual);
    }

    public static class CustomDateComparator implements CustomPropertyComparator<Date, ValueChange> {

        @Override
        public ValueChange compare(final Date left, final Date right, final GlobalId affectedId, final Property property) {
            boolean isSame = false;
            if ((left == null) && (right == null)) {
                isSame = true;
            } else if ((left != null) && (right != null)) {
                if (((AccessibleObject) property.getMember().getRawMember()).isAnnotationPresent(Temporal.class)) {
                    final TemporalType temporalType = ((AccessibleObject) property.getMember().getRawMember()).getAnnotation(Temporal.class).value();
                    if (temporalType == TemporalType.DATE) {
                        isSame = DateUtils.isSameDay(left, right);
                    } else if (temporalType == TemporalType.TIMESTAMP) {
                        isSame = DateUtils.isSameInstant(left, right);
                    }
                } else {
                    isSame = DateUtils.isSameInstant(left, right);
                }
            }

            if (isSame) {
                return null;
            }

            return new ValueChange(affectedId, property.getName(), left, right);
        }
    }


}
