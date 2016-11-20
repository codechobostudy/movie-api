package io.codechobo.member.domain.support;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author loustler
 * @since 10/02/2016 10:15
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SocialDto {
    private Long sequence;
    private String type;
    private String token;
    private Long memberSequence;

    /**
     * Builder pattern
     *
     * @param builder
     */
    private SocialDto(final Builder builder) {
        this.sequence = builder.sequence;
        this.type = builder.type;
        this.token = builder.token;
        this.memberSequence = builder.memberSequence;
    }

    public static class Builder {
        private Long sequence = null;
        private String type = null;
        private String token = null;
        private Long memberSequence = null;

        public Builder() {}

        public Builder sequence(@NotNull final Long sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder type(@NotNull final String type) {
            this.type = type;
            return this;
        }

        public Builder token(@NotNull final String token) {
            this.token = token;
            return this;
        }

        public Builder memberSequence(@NotNull final Long memberSequence) {
            this.memberSequence = memberSequence;
            return this;
        }

        public SocialDto build() {
            return new SocialDto(this);
        }
    }
}
