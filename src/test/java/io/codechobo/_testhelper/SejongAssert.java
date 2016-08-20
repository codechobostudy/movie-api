package io.codechobo._testhelper;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.core.util.VisibleForTesting;

import java.lang.reflect.Field;
import java.util.StringJoiner;

import static org.assertj.core.error.ShouldBeEqualIgnoringCase.shouldBeEqual;

public class SejongAssert<A> extends AbstractObjectAssert<SejongAssert<A>, A> {

    @VisibleForTesting
    private Failures failures = Failures.instance();

    protected SejongAssert(A target) {
        super(target, SejongAssert.class);
    }

    public SejongAssert<A> is(String filedPath, Object expected) {
        assertEquals(info, getValue(filedPath, actual), expected);
        return this;
    }

    private boolean checkEqual(Object actual, Object expected) {
        if (actual == null) return expected == null;
        if (expected == null) return false;

        return actual.equals(expected);
    }

    private void assertEquals(AssertionInfo info, Object actual, Object expected) {
        if (!checkEqual(actual, expected))
            throw failures.failure(info, shouldBeEqual(actual.toString(), expected.toString()));
    }


    private Object getValue(String path, Object target) {
        // 하위 객체를 찾아야 할 경우
        if (path.contains(".")) {
            String[] paths = path.split("\\.");
            Object newTarget = findInstance(target, paths[0]);
            StringJoiner joiner = new StringJoiner(".");
            for (int i = 1; i < paths.length; i++) joiner.add(paths[i]);

            return getValue(joiner.toString(), newTarget);
        } else {
            return findInstance(target, path);
        }
    }

    private Object findInstance(Object target, String name) {

        try {
            Field field = getAllDeclaredField(target.getClass(), name);
            field.setAccessible(true);
            return field.get(target);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    // super 클래스 field의 값을 반환합니다.
    private static Field getAllDeclaredField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> current = clazz;
        do {
            try {
                return current.getDeclaredField(fieldName);
            } catch(NoSuchFieldException e) {
                if(current.getSuperclass() == null){
                    throw e;
                }
            }
        } while((current = current.getSuperclass()) != null);
        throw new RuntimeException("field를 찾지 못함 : " + fieldName);
    }

    public static <T> SejongAssert<T> objectAssertThat(T object) {
        return new SejongAssert<>(object);
    }
}