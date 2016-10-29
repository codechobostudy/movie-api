package io.codechobo.member.domain.util;

import io.codechobo.member.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

/**
 * @author loustler
 * @since 10/26/2016 00:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class EntityDtoConverterIntegrationTest {
    @Test
    public void class_get_methods() {
        Class entityC = new EntityDtoConverter().getClass();
        for(Method method : entityC.getMethods())
            System.out.println(method.getName());
    }

    @Test
    public void class_get_specify_method() throws Exception{
        String methodName = "memberConvertToDto";
        Class entityC = new EntityDtoConverter().getClass();
        Method method = null;
        try {
            method = entityC.getMethod(methodName, new Class[]{Member.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for(Class parameter : method.getParameterTypes())
            System.out.println(parameter.getName());
    }

    @Test
    public void method_invoke_test() throws Exception{
        Method method = Integer.class.getMethod("parseInt", String.class);

        System.out.println(method.invoke(method, "5").toString());
    }
}
