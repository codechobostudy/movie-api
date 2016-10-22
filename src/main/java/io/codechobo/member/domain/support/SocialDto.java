package io.codechobo.member.domain.support;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author loustler
 * @since 10/02/2016 10:15
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
public class SocialDto {
    private Long sequence;
    private String type;
    private String token;
    private Long memberSequence;

    /**
     *
     * @param sequence          소셜 시퀀스
     */
    public SocialDto(final Long sequence) {
        this.sequence = sequence;
    }

    /**
     *
     * @param type              소셜 타입
     * @param token             소셜 액세스 토큰
     * @param memberSequence    소셜을 가진 멤버의 시퀀스
     */
    public SocialDto(final String type, final String token, final Long memberSequence) {
        this(null, type, token, memberSequence);
    }

    /**
     *
     * @param sequence          소셜 시퀀스
     * @param type              소셜 타입
     * @param memberSequence    소셜을 가진 멤버의 시퀀스
     */
    public SocialDto(final Long sequence, final String type, final Long memberSequence) {
        this(sequence, type, null, memberSequence);
    }

    /**
     *
     * @param sequence          소셜 타입
     * @param type              소셜 타입
     * @param token             소셜 액세스 토큰
     * @param memberSequence    소셜을 가진 멤버의 시퀀스
     */
    public SocialDto(final Long sequence, final String type, final String token, final Long memberSequence) {
        this.sequence = sequence;
        this.type = type;
        this.token = token;
        this.memberSequence = memberSequence;
    }
}
