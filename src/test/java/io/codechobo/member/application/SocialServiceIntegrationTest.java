package io.codechobo.member.application;

import io.codechobo.member.domain.support.MemberDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author loustler
 * @since 10/25/2016 23:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class SocialServiceIntegrationTest {
    @Autowired
    private SocialService socialService;

    @Autowired
    private MemberService memberService;

    private MemberDto memberDto;

    @Before
    public void set_up() {
        this.memberDto = memberService.createMember(new MemberDto.Builder()
                .id("loustler")
                .password("loustlerPw")
                .email("dev.loustler@gmail.com")
                .point(new Integer(500))
                .nickName("loustler")
                .build()
        );
    }

    @Test
    public void config_test() {
        System.out.println(this.memberDto);
    }
}
