package io.codechobo.member.application;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.PointPerLevel;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Get mebmer list.
     *
     * @return MemberDto list
     */
    public List<MemberDto> getMembers() {
        List<Member> member = memberRepository.findAll();

        return member.stream()
                .map(m -> new MemberDto(m.getSeq(), m.getId(), m.getPassword(), m.getEmail(), m.getNickName(), m.getRegistrationDate(), m.getPoint()))
                .collect(Collectors.toList());
    }

    /**
     * Get one Member using member's sequence.
     *
     * @param sequence
     * @return Null | MemberDto
     */
    public MemberDto getMember(final Long sequence) {
        Member member = memberRepository.findOne(sequence);

        if(member == null) return null;

        return new MemberDto(member.getSeq(), member.getId(), member.getPassword(), member.getEmail(), member.getNickName(), member.getRegistrationDate(), member.getPoint());
    }

    /**
     * Create member using memberDTO.
     *
     * @param memberDto
     * @return MemberDto
     */
    @Transactional
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

    /**
     * Update member using memberDTO.
     *
     * @param memberDto
     * @return MemberDto
     * @throws IllegalArgumentException in case member's sequence is null or member's member is not exist.
     */
    @Transactional
    public MemberDto updateMember(MemberDto memberDto) {
        if(memberDto.getSequence() == null || !memberRepository.exists(memberDto.getSequence())) {
            throw new IllegalArgumentException();
        }

        memberDto.setLevel(PointPerLevel.valueOf(memberDto.getPoint()));

        Member member = memberRepository.save(new Member(memberDto));

        return new MemberDto(member.getSeq(), member.getId(), member.getPassword(), member.getEmail(), member.getNickName(), member.getRegistrationDate(), member.getPoint());
    }

    /**
     * Delete member using member sequence.
     *
     * @param memberSequence
     * @throws IllegalArgumentException in case member sequence is null.
     */
    @Transactional
    public void deleteMember(@NotNull final Long memberSequence) {
        if(memberSequence == null) {
            throw new IllegalArgumentException();
        }

        memberRepository.delete(memberSequence);
    }
}
