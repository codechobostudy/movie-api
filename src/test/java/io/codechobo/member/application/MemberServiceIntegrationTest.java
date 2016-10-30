package io.codechobo.member.application;

import io.codechobo.member.domain.PointPerLevel;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author loustler
 * @since 10/02/2016 10:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;

    @Test
    public void 멤버등록_테스트_crate_member() {
        // given & when
        final MemberDto dto = generateData();
        final MemberDto member = memberService.createMember(dto);

        // then
        assertNotNull(member);
        assertThat(member.getNickName(), is("nickName"));
        System.out.println(member.getLevel());
    }

    @Test
    public void 멤버_업데이트_update_member() {
        // given
        final MemberDto dto = generateData();
        final MemberDto member = memberService.createMember(dto);

        final MemberDto updateDto = new MemberDto.Builder()
                .sequence(member.getSequence())
                .id(member.getId())
                .password("another password")
                .email("loustler@gmail.com")
                .nickName("loustler")
                .regiDate(Calendar.getInstance().getTime())
                .point(new Integer(100))
                .build();
        memberService.updateMember(updateDto);

        // when
        final MemberDto updateMember = memberService.getMember(member.getSequence());

        // then
        assertNotNull(updateMember);
        assertThat(updateMember.getNickName(), is("loustler"));
        assertThat(updateMember.getLevel(), is(PointPerLevel.STANDARD));
    }

    @Test
    public void 멤버_삭제_remove_member() {
        // given
        final MemberDto dto = generateData();
        final MemberDto member = memberService.createMember(dto);

        memberService.deleteMember(member.getSequence());

        // when
        MemberDto after = memberService.getMember(member.getSequence());

        // then
        assertNull(after);
    }

    @Test
    public void 멤버_리스트_get_members() {
        // given
        List<MemberDto> memberDtoList = memberService.getMembers();

        // when

        // then
        assertThat(memberDtoList.size(), is(0));
        assertNotNull(memberDtoList);

    }

    private MemberDto generateData() {
        return new MemberDto.Builder()
                .id("id")
                .password("password")
                .email("email@gmail.com")
                .nickName("nickName")
                .regiDate(Calendar.getInstance().getTime())
                .point(new Integer(0))
                .build();
    }
}
