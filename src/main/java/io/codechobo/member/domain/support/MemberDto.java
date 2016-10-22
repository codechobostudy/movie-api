package io.codechobo.member.domain.support;

import io.codechobo.member.domain.PointPerLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author loustler
 * @since 10/02/2016 10:13
 */
@Accessors(chain = true)
@Data
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

    /**
     *
     * @param sequence  멤버 시퀀스
     */
    public MemberDto(final Long sequence) {
        this.sequence = sequence;
    }

    /**
     *
     * @param id        멤버 아이디
     * @param password  멤버 패스워드
     * @param email     멤버 이메일
     * @param nickName  멤버 닉네임
     */
    public MemberDto(final String id, final String password, final String email, final String nickName) {
        this(null, id, password, email, nickName, null, 0);
    }

    /**
     *
     * @param id        멤버 아이디
     * @param password  멤버 패스워드
     * @param email     멤버 이메일
     * @param nickName  멤버 닉네임
     * @param regiDate  멤버 가입일
     * @param point     멤버 포인트
     */
    public MemberDto(final String id, final String password, final String email, final String nickName, final Date regiDate, final Integer point) {
        this(null, id, password, email, nickName, regiDate, point);
    }

    /**
     *
     * @param sequence  멤버 시퀀스
     * @param id        멤버 아이디
     * @param password  멤버 패스워드
     * @param email     멤버 이메일
     * @param nickName  멤버 닉네임
     * @param regiDate  멤버 가입일
     * @param point     멤버 포인트
     */
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
