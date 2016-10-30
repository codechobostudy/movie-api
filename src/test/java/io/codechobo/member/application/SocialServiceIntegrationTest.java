package io.codechobo.member.application;

import io.codechobo.member.domain.support.MemberDto;
import io.codechobo.member.domain.support.SocialDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
    private SocialDto socialDto;

    @Before
    public void set_up() {
        this.memberDto = memberDtoFactory();
        this.socialDto = socialDtoFactory();
        System.out.println(this.socialDto);
        System.out.println(this.memberDto);
    }

    @Test
    public void config_test() {
        System.out.println(this.socialDto);
        System.out.println(this.memberDto);
    }

    /**
     * @see SocialService#createSocial(SocialDto)
     */
    @Test
    public void create_social() {
        // given
        SocialDto createdSocial = socialDtoFactory(new SocialDto.Builder().type("google").token("sldkfjsdklf123b45k4dkie").memberSequence(this.memberDto.getSequence()).build());
        assertNotNull(createdSocial.getMemberSequence());

        // then
        assertNotNull(createdSocial);
        assertThat(createdSocial.getMemberSequence(), is(this.memberDto.getSequence()));
    }

    @Test
    public void get_socials() {
        // given
        socialDtoFactory(new SocialDto.Builder().memberSequence(this.memberDto.getSequence()).token("asfasdfsdf12313adf").type("twitter").build());

        // when
        List<SocialDto> dtoList = socialService.getSocials(this.memberDto.getSequence());

        // then
        assertNotNull(dtoList);
        dtoList.forEach(d -> System.out.println(d));
    }

    @Test
    public void update_social() {
        // given
        SocialDto update = this.socialDto;
        final String token = "ajs7ds8f6sdf6s5df4szds6ffsdf4";
        update.setType("twitter").setToken(token);

        if(Objects.isNull(update.getMemberSequence()))
            update.setMemberSequence(this.memberDto.getSequence());

        // when
        SocialDto updated = socialService.updateSocial(update);

        // then
        assertNotNull(updated);
        assertThat(updated.getToken(), is(token));
        System.out.println(updated);
    }

    @Test
    public void delete_social() {
        // given
        SocialDto delete = this.socialDto;

        // when
        socialService.deleteSocial(delete.getSequence());

        // then
        // throw NullPointException is success.
        try {
            SocialDto deleted = socialService.getSocial(delete.getSequence());
        } catch (NullPointerException e) {
            System.out.println("success");
        }
    }

    private MemberDto memberDtoFactory() {
        return memberService.createMember(new MemberDto.Builder()
                .id("loustler")
                .password("loustlerPw")
                .email("dev.loustler@gmail.com")
                .point(new Integer(500))
                .nickName("loustler")
                .build()
        );
    }

    private SocialDto socialDtoFactory() {
        return socialDtoFactory(new SocialDto.Builder().type("github").token("2kd8fd7s9xkcfsl22kldkfysdf1").memberSequence(this.memberDto.getSequence()).build());
    }

    private SocialDto socialDtoFactory(SocialDto socialDto) {
        return socialService.createSocial(socialDto.setMemberSequence(this.memberDto.getSequence()));
    }
}
