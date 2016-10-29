package io.codechobo.member.domain.util;

import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.Social;
import io.codechobo.member.domain.support.MemberDto;
import io.codechobo.member.domain.support.SocialDto;

import java.util.Objects;

/**
 * @author loustler
 * @since 10/25/2016 13:08
 */
public class EntityDtoConverter {

    /**
     * Social Entity convert to SocialDto
     *
     * @param social
     * @return SocialDto
     * @throws NullPointerException in case social is null
     */
    public static SocialDto socialConvertToDto(Social social) {
        social = Objects.requireNonNull(social);

        return new SocialDto.Builder()
                .sequence(social.getSeq())
                .type(social.getType())
                .token(social.getToken())
                .memberSequence(social.getMember().getSeq())
                .build();
    }

    /**
     * SocialDto convert to Social
     *
     * @param socialDto
     * @return Social
     * @throws NullPointerException in case socialDto is null
     */
    public static Social socialDtoConvertToEntity(SocialDto socialDto) {
        Social social = new Social(Objects.requireNonNull(socialDto));
        Member member = new Member(new MemberDto());

        member.setSeq(socialDto.getMemberSequence());
        social.setMember(member);

        return social;
    }

    /**
     * Member Entity convert to MemberDto
     *
     * @param member
     * @return MemberDto
     * @throws NullPointerException in case member is null
     */
    public static MemberDto memberConvertToDto(Member member) {
        member = Objects.requireNonNull(member);

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

    /**
     * MemberDto convert to Member
     *
     * @param memberDto
     * @return Member
     * @throws NullPointerException in case memberdto is null
     */
    public static Member memberDtoConvertToEntity(MemberDto memberDto) {
        return new Member(Objects.requireNonNull(memberDto));
    }

    /*
    public static <T> List<T> entityListConvertToDtoList(List<T> entityList, T t) {
        Class entityC = EntityDtoConverter.class;
        String methodName = null;

        if (t instanceof Member) methodName = "memberConvertToDto";
        else if (t instanceof Social) methodName = "socialConvertToDto";
        else throw new IllegalArgumentException();

        Method method = null;
        try {
            method = entityC.getMethod(methodName, new Class[]{t.getClass()});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        final Method realMethod = method;

        List<T> result = new ArrayList<T>(entityList.size());

        try {
            result = Collections.synchronizedList(entityList).parallelStream()
//                    .map(c -> realMethod.invoke(null, c))
                    .collect(Collectors.toList());
        } catch (Exception invoE) {
            result = null;
        }

        return result;
    }
    */
}
