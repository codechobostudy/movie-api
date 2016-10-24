package io.codechobo.member.application;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.Social;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.repository.SocialRepository;
import io.codechobo.member.domain.support.SocialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author loustler
 * @since 10/24/2016 23:42
 */
@Service
public class SocialService {
    @Autowired
    private SocialRepository socialRepository;
    @Autowired
    private MemberRepository memberRepository;

    /**
     * Get multi social using member sequence.
     *
     * @param memberSequence
     * @return
     */
    public List<SocialDto> getSocials(@NotNull final Long memberSequence) {
        return socialRepository.findByMemberSequence(memberSequence)
                .stream()
                .map(s -> new SocialDto(s.getSeq(), s.getType(), s.getMember().getSeq()))
                .collect(Collectors.toList());
    }

    /**
     * Get single social using social sequence.
     *
     * @param socialSequence
     * @return SocialDto
     */
    public SocialDto getSocial(@NotNull final Long socialSequence) {
        Social foundSocial = socialRepository.findOne(socialSequence);

        return new SocialDto(foundSocial.getSeq(), foundSocial.getType(), foundSocial.getMember().getSeq());
    }

    /**
     * Create SocialDto using member sequence in socialDto.
     *
     * @param socialDto
     * @return SocialDto
     * @throws IllegalArgumentException in case socialDto's member sequence is null
     */
    @Transactional
    public SocialDto createSocial(@NotNull final SocialDto socialDto) {
        if(socialDto.getMemberSequence() == null)
            throw new IllegalArgumentException("SocialDto's mebmer sequence must be not null.");

        Social social = new Social(socialDto);

        Member foundMember = memberRepository.findOne(socialDto.getMemberSequence());

        social.setMember(foundMember);

        Social created = socialRepository.save(social);

        return new SocialDto(created.getSeq(), created.getType(), created.getMember().getSeq());
    }

    /**
     * Update Social using Social Sequence.
     *
     * @param socialDto
     * @return SocialDto
     * @throws IllegalArgumentException in case social sequence is null or not exist.
     */
    @Transactional
    public SocialDto updateSocial(@NotNull final SocialDto socialDto) {
        if(socialDto.getSequence() == null)
            throw new IllegalArgumentException("Social update failed. social sequence required.");

        if(!socialRepository.exists(socialDto.getSequence()))
            throw new IllegalArgumentException(socialDto.getSequence()+" is not exist.");

        Social updateSocial = new Social(socialDto);

        updateSocial = socialRepository.save(updateSocial);

        return new SocialDto(updateSocial.getSeq(), updateSocial.getType(), updateSocial.getMember().getSeq());
    }

    /**
     * Delete social using social sequence.
     *
     * @param socialSequence NotNull
     */
    @Transactional
    public void deleteSocial(@NotNull final Long socialSequence) {
        socialRepository.delete(socialSequence);
    }
}
