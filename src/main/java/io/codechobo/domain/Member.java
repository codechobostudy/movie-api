package io.codechobo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_seq", insertable = false, updatable = false)
    private Integer seq; // 시퀀스

    @Id
    @Column(name = "member_id")
    private String id; // Id Uniq

    @Column(name = "member_password")
    private String pwd; // 패스워드

    @Column(name = "member_nickname")
    private String nickName; // 닉네임 uqniq

    @Id
    @Column(name = "member_email")
    private String email; // 이메일 uniq

    @Column(name = "member_reg_date", updatable = false)
    private Date regDate; // 회원가입일

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_seq")
    private Level level;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_seq")
    private Social social;

}
