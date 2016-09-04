package io.codechobo.member.interfaces.api;

import io.codechobo.member.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Loustler
 * @since 8/29/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
public class MemberRestControllerIntegrationTest {

    @Autowired private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired private MemberRepository memberRepository;


    @Test
    public void member_save_rest_controller_test() {

    }
}
