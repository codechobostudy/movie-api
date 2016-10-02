package io.codechobo.member.domain;


import io.codechobo.member.domain.support.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_SEQ", updatable = false)
    private Long seq; // 시퀀스

    @Column(name = "MEMBER_ID", unique = true)
    private String id; // Id Uniq

    @Column(name = "PASSWORD")
    private String password; // 패스워드

    @Column(name = "MEMBER_NICKNAME", unique = true)
    private String nickName; // 닉네임 uniq

    @Column(name = "MEMBER_EMAIL", unique = true)
    private String email; // 이메일 uniq

    @Column(name = "MEMBER_REG_DATE", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate; // 회원가입일

    /*
     * Join시 level은 반드시 member에게 존재하여야 함(optional false로, false시 not-null)
     * level은 member에 종속적이므로 cascade
     * Level 분리 하지 않고 Member에 귀속
     */
    @Column(name = "MEMBER_LEVEL")
    @Enumerated(EnumType.STRING)
    private PointPerLevel level;

    @Column(name = "MEMBER_POINT")
    private Integer point;

    /*
     * social은 member에 종속적이므로 cascade
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seq")
    private List<Social> socials;

    public Member(final MemberDto memberDto) {
        this.seq = memberDto.getSequence();
        this.id = memberDto.getId();
        this.password = memberDto.getPassword();
        this.nickName = memberDto.getNickName();
        this.email = memberDto.getEmail();
        this.point = memberDto.getPoint();
        this.level = memberDto.getLevel();
        this.registrationDate = memberDto.getRegiDate();
    }

    @PrePersist
    public void tmpLevelPersist() {
        this.level = PointPerLevel.valueOf(this.point);
    }
}
