package io.codechobo.member.domain.support;

import io.codechobo.member.domain.PointPerLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author loustler
 * @since 10/02/2016 10:13
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private Long sequence;
    private String id;
    private String password;
    private String email;
    private String nickName;
    private Date regiDate;
    private Integer point;
    private PointPerLevel level;

    public MemberDto(final Long sequence) {
        this.sequence = sequence;
    }

    public MemberDto(final String id, final String password, final String email, final String nickName) {
        this(null, id, password, email, nickName, null, 0);
    }

    public MemberDto(final String id, final String password, final String email, final String nickName, final Date regiDate, final Integer point) {
        this(null, id, password, email, nickName, regiDate, point);
    }

    public MemberDto(final Long sequence, final String id, final String password, final String email, final String nickName, final Date regiDate, final Integer point) {
        this.sequence = sequence;
        this.id = id;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.regiDate = regiDate;
        this.point = point;
        this.level = PointPerLevel.valueOf(this.point);
    }
}
