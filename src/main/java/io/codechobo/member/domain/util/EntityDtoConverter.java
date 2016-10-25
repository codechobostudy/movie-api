package io.codechobo.member.domain.util;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.Social;
import io.codechobo.member.domain.support.MemberDto;
import io.codechobo.member.domain.support.SocialDto;

/**
 * @author loustler
 * @since 10/25/2016 13:08
 */
public class EntityDtoConverter {

    public static SocialDto socialConvertToDto(Social social) {
        return new SocialDto.Builder()
                .sequence(social.getSeq())
                .type(social.getType())
                .token(social.getToken())
                .memberSequence(social.getMember().getSeq())
                .build();
    }

    public static Social socialDtoConvertToEntity(SocialDto socialDto) {
        Social social = new Social(socialDto);
        Member member = new Member(new MemberDto());

        member.setSeq(socialDto.getMemberSequence());
        social.setMember(member);

        return social;
    }

    public static MemberDto memberConvertToDto(Member member) {
        return new MemberDto.Builder()
                .sequence(member.getSeq())
                .id(member.getId())
                .password(member.getPassword())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .point(member.getPoint())
                .regiDate(member.getRegistrationDate())
                .level(member.getLevel())
                .build();
    }

    public static Member memberDtoConvertToEntity(MemberDto memberDto) {
        return new Member(memberDto);
    }
}
