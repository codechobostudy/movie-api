package io.codechobo.member.application;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.PointPerLevel;
import io.codechobo.member.domain.Social;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import io.codechobo.member.domain.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @author loustler
 * @since 10/02/2016 10:12
 */
@Service
@Transactional
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

        return EntityDtoConverter.memberListConvertToDtoList(member);
    }

    /**
     * Get one Member using member's sequence.
     *
     * @param sequence
     * @return Null | MemberDto
     */
    public MemberDto getMember(final Long sequence) {
        Member member = memberRepository.findOne(Objects.requireNonNull(sequence));

        if(Objects.isNull(member)) return null;

        return EntityDtoConverter.memberConvertToDto(member);
    }

    /**
     * Create member using memberDTO.
     *
     * @param memberDto
     * @return MemberDto
     */
    public MemberDto createMember(final MemberDto memberDto) {
        Member member = new Member(Objects.requireNonNull(memberDto));

        if(Objects.isNull(member.getPoint()))
            member.setPoint(new Integer(0));

        if(Objects.isNull(member.getRegistrationDate()))
            member.setRegistrationDate(Calendar.getInstance().getTime());

        member.setLevel(PointPerLevel.valueOf(member.getPoint()));

        member.setSocials(new ArrayList<Social>());

        Member createdMember = memberRepository.save(member);

        return EntityDtoConverter.memberConvertToDto(createdMember);
    }

    /**
     * Update member using memberDTO.
     *
     * @param memberDto
     * @return MemberDto
     * @throws EntityNotFoundException member is not exist.
     * @throws NullPointerException in case member sequence is null/
     */
    public MemberDto updateMember(MemberDto memberDto) {

        if(!memberRepository.exists(Objects.requireNonNull(memberDto.getSequence()))) {
            throw new EntityNotFoundException();
        }

        memberDto.setLevel(PointPerLevel.valueOf(memberDto.getPoint()));

        Member member = memberRepository.save(new Member(memberDto));

        return EntityDtoConverter.memberConvertToDto(member);
    }

    /**
     * Delete member using member sequence.
     *
     * @param memberSequence
     * @throws NullPointerException in case member sequence is null.
     */
    public void deleteMember(@NotNull final Long memberSequence) {
        memberRepository.delete(Objects.requireNonNull(memberSequence));
    }
}
