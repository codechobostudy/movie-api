package io.codechobo.member.domain.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author loustler
 * @since 10/25/2016 12:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class MemberDtoIntegrationTest {
    @Test
    public void builder_pattern_test(){
    }
}
