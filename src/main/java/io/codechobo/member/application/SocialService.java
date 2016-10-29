package io.codechobo.member.application;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.Social;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.repository.SocialRepository;
import io.codechobo.member.domain.support.SocialDto;
import io.codechobo.member.domain.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
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
        return socialRepository.findByMemberSeq(memberSequence)
                .stream()
                .map(s -> EntityDtoConverter.socialConvertToDto(s))
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

        return EntityDtoConverter.socialConvertToDto(foundSocial);
    }

    /**
     * Create SocialDto using member sequence in socialDto.
     *
     * @param socialDto
     * @return SocialDto
     * @throws NullPointerException in case socialDto's member sequence is null
     */
    @Transactional
    public SocialDto createSocial(@NotNull final SocialDto socialDto) {
        Member foundMember = memberRepository.findOne(Objects.requireNonNull(socialDto.getMemberSequence()));

        Social social = EntityDtoConverter.socialDtoConvertToEntity(socialDto);

        social.setMember(foundMember);

        Social created = socialRepository.save(social);

        return EntityDtoConverter.socialConvertToDto(created);
    }

    /**
     * Update Social using Social Sequence.
     *
     * @param socialDto
     * @return SocialDto
     * @throws EntityNotFoundException in case social sequence is null or not exist.
     * @throws NullPointerException in case social sequence is null.
     */
    @Transactional
    public SocialDto updateSocial(@NotNull final SocialDto socialDto) {
        if (!socialRepository.exists(Objects.requireNonNull(socialDto.getSequence())))
            throw new EntityNotFoundException(socialDto.getSequence()+" is not exist.");

        Social updateSocial = new Social(socialDto);

        updateSocial = socialRepository.save(updateSocial);

        return EntityDtoConverter.socialConvertToDto(updateSocial);
    }

    /**
     * Delete social using social sequence.
     *
     * @param socialSequence NotNull
     * @throws NullPointerException in case social sequence is null.
     */
    @Transactional
    public void deleteSocial(@NotNull final Long socialSequence) {
        socialRepository.delete(Objects.requireNonNull(socialSequence));
    }
}
