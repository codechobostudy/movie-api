package io.codechobo.member.domain.support;

import io.codechobo.member.domain.PointPerLevel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author loustler
 * @since 10/02/2016 10:13
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDto {
    private Long sequence;
    private String id;
    private String password;
    private String email;
    private String nickName;
    private Date regiDate;
    private Integer point;
    private PointPerLevel level;
    private List<SocialDto> socialDtoList;

    private MemberDto(Builder builder) {
        this.sequence = builder.sequence;
        this.id = builder.id;
        this.password = builder.password;
        this.email = builder.email;
        this.nickName = builder.nickName;
        this.regiDate = builder.regiDate;
        this.point = builder.point;
        this.level = builder.level;
        this.socialDtoList = builder.socialDtoList;
    }

    public static class Builder {
        private Long sequence = null;
        private String id = null;
        private String password = null;
        private String email = null;
        private String nickName = null;
        private Date regiDate = null;
        private Integer point = null;
        private PointPerLevel level = null;
        private List<SocialDto> socialDtoList = null;

        public Builder() {}

        public Builder sequence(@NotNull final Long sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder id(@NotNull final String id) {
            this.id = id;
            return this;
        }

        public Builder password(@NotNull final String password) {
            this.password = password;
            return this;
        }

        public Builder email(@NotNull final  String email) {
            this.email = email;
            return this;
        }

        public Builder nickName(@NotNull final String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder regiDate(@NotNull final Date regiDate) {
            this.regiDate = regiDate;
            return this;
        }

        public Builder point(@NotNull final Integer point) {
            this.point = point;
            return this;
        }

        public Builder level(@NotNull final PointPerLevel level) {
            this.level = level;
            return this;
        }

        public Builder socials(@NotNull final List<SocialDto> socialDtoList) {
            this.socialDtoList = socialDtoList;
            return this;
        }

        public Builder social(@NotNull final SocialDto socialDto) {
            if(Objects.isNull(this.socialDtoList))
                this.socialDtoList = new ArrayList<>();

            this.socialDtoList.add(socialDto);

            return this;
        }

        public MemberDto build() {
            return new MemberDto(this);
        }
    }
}
