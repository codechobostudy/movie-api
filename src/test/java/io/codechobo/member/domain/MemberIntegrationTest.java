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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
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
    public void 설정확인_config_test() {
        System.out.println(this.member);
    }

    @Test
    public void 멤버생성_create_new_member() {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setId("test");
        memberDto.setPassword("password");
        memberDto.setNickName("loustler2");
        memberDto.setEmail("dev.loustler@gmail.com");
        memberDto.setPoint(new Integer(0));
        memberDto.setSocials(null);

        //when
        Member saveMember = memberRepository.save(new Member(memberDto));

        // then
        assertThat(saveMember.getLevel(), is(PointPerLevel.BASIC));
        assertNotNull(saveMember.getSeq());
        System.out.println(saveMember);
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
        MemberDto memberDto = new MemberDto();
        memberDto.setId("any2");
        memberDto.setPassword("password");
        memberDto.setNickName("anonymouse2");
        memberDto.setEmail("email2@gmail.com");
        memberDto.setPoint(new Integer(500));
        memberDto.setSocials(null);

        Member newMember = new Member(memberDto);

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

        this.memberRepository.save(this.member);

        this.member = this.memberRepository.findOne(this.member.getSeq());

        assertThat(this.member.getPoint(), is(2));
    }

    private Member memberFactory() {
        MemberDto memberDto = new MemberDto();
        memberDto.setId("any1");
        memberDto.setPassword("password");
        memberDto.setNickName("anonymouse");
        memberDto.setEmail("email@gmail.com");
        memberDto.setPoint(new Integer(0));
        memberDto.setSocials(null);

        return memberRepository.save(new Member(memberDto));
    }
}