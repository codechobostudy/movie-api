package io.codechobo.member.application;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.PointPerLevel;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author loustler
 * @since 10/02/2016 10:12
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<MemberDto> getMembers() {
        List<Member> member = memberRepository.findAll();

        return member.stream()
                .map(m -> new MemberDto(m.getSeq(), m.getId(), m.getPassword(), m.getEmail(), m.getNickName(), m.getRegistrationDate(), m.getPoint()))
                .collect(Collectors.toList());
    }

    public MemberDto getMember(final Long sequence) {
        Member member = memberRepository.findOne(sequence);

        if(member == null) return null;

        return new MemberDto(member.getSeq(), member.getId(), member.getPassword(), member.getEmail(), member.getNickName(), member.getRegistrationDate(), member.getPoint());
    }

    public MemberDto createMember(final MemberDto memberDto) {
        Member member = new Member(memberDto);

        if(member.getPoint() == null)
            member.setPoint(new Integer(0));

        if(member.getRegistrationDate() == null)
            member.setRegistrationDate(Calendar.getInstance().getTime());

        member.setLevel(PointPerLevel.valueOf(member.getPoint()));

        member = memberRepository.save(member);

        return new MemberDto(member.getSeq(), member.getId(), member.getPassword(), member.getEmail(), member.getNickName(), member.getRegistrationDate(), member.getPoint());
    }

    public MemberDto updateMember(MemberDto memberDto) {
        if(memberDto.getSequence() == null || !memberRepository.exists(memberDto.getSequence())) {
            throw new IllegalArgumentException();
        }

        memberDto.setLevel(PointPerLevel.valueOf(memberDto.getPoint()));

        Member member = memberRepository.save(new Member(memberDto));

        return new MemberDto(member.getSeq(), member.getId(), member.getPassword(), member.getEmail(), member.getNickName(), member.getRegistrationDate(), member.getPoint());
    }

    public void deleteMember(@NotNull final Long memberSequence) {
        if(memberSequence == null) {
            throw new IllegalArgumentException();
        }

        memberRepository.delete(memberSequence);
    }
}
