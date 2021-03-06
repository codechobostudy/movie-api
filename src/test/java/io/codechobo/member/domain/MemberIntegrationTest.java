package io.codechobo.member.domain;

import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author loustler
 * @since 10/02/2016 10:21
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class MemberIntegrationTest {
    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @Before
    public void setUp() {
        this.member = this.memberFactory();
    }

    @After
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    public void 설정확인_config_test() {}

    @Test
    public void 멤버생성_create_new_member() {
        // given
        Member member = new Member(
                new MemberDto.Builder()
                        .id("test")
                        .password("password")
                        .email("dev.loustler@gmail.com")
                        .nickName("loustler")
                        .regiDate(Calendar.getInstance().getTime())
                        .point(new Integer(0))
                        .build()
        );

        // when
        Member saveMember = memberRepository.save(member);

        // then
        assertThat(saveMember.getLevel(), is(PointPerLevel.BASIC));
        assertNotNull(saveMember);
        assertNotNull(saveMember.getSeq());
    }

    @Test
    public void 멤버업데이트_update_member() {
        // given
        member.setNickName("users");

        // when
        memberRepository.save(member);
        Member afterMember = memberRepository.findByNickName("users");

        // then
        assertThat(afterMember.getNickName(), is("users"));
    }

    @Test
    public void 멤버찾기_find_member() {
        // given & when
        Member findMember = memberRepository.findByNickName("anonymouse");
        Member findMember2 = memberRepository.findByEmail(this.member.getEmail());

        // then
        assertNotNull(findMember);
        assertNotNull(findMember2);
        assertThat(findMember.getNickName(), is("anonymouse"));
        assertThat(findMember2.getEmail(), is(this.member.getEmail()));

    }

    @Test
    public void 멤버레벨업_level_up_member() {
        // given
        Member newMember = new Member(
                new MemberDto.Builder()
                .id("any2")
                .password("password")
                .email("email2@gmail.com")
                .nickName("anonymouse2")
                .regiDate(Calendar.getInstance().getTime())
                .point(new Integer(500))
                .build()
        );

        // when
        memberRepository.save(newMember);

        // then
        Member findMember = memberRepository.findByNickName(newMember.getNickName());
        assertNotNull(findMember);
        assertThat(findMember.getLevel(), is(PointPerLevel.VVIP));
    }

    @Test
    public void 예매등의행위로_포인트증가_increase_point() {
        this.member = memberRepository.save(this.member);

        assertThat(this.member.getPoint(), is(1));

        this.member.setPoint(this.member.getPoint()+1);

        this.memberRepository.save(this.member);

        this.member = this.memberRepository.findOne(this.member.getSeq());

        assertThat(this.member.getPoint(), is(2));
    }

    private Member memberFactory() {
        Member member = new Member(
                new MemberDto.Builder()
                .id("any1")
                .password("password")
                .email("email@gmail.com")
                .nickName("anonymouse")
                .regiDate(Calendar.getInstance().getTime())
                .point(new Integer(0))
                .build()
        );
        return memberRepository.save(member);
    }
}
