package io.codechobo.member.domain.intefaces;

import io.codechobo.member.interfaces.api.MemberRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Loustler
 * @since 8/29/16
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class MemberRestControllerIntegrationTest {

    @Autowired
    MemberRestController memberRestController;

    @Test
    public void member_save_rest_controller_test() {

    }
}
