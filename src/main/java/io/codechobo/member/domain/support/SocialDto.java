package io.codechobo.member.domain.support;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author loustler
 * @since 10/02/2016 10:15
 */
@Getter
@Setter
@NoArgsConstructor
public class SocialDto {
    private Long sequence;
    private String type;
    private String token;
    private Long memberSequence;

    public SocialDto(final Long sequence) {
        this.sequence = sequence;
    }

    public SocialDto(final String type, final String token, final Long memberSequence) {
        this(null, type, token, memberSequence);
    }

    public SocialDto(final Long sequence, final String type, final String token, final Long memberSequence) {
        this.sequence = sequence;
        this.type = type;
        this.token = token;
        this.memberSequence = memberSequence;
    }
}
