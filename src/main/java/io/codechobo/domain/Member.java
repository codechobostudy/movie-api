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
    @Column(name = "member_seq", updatable = false)
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

    /*
     * 회원가입일은 변경될 일이 없기때문에 update 못하게
     */
    @Column(name = "member_reg_date", updatable = false)
    private Date regDate; // 회원가입일

    /*
     * Join시 level은 반드시 member에게 존재하여야 함
     * level은 member에 종속적이므로 cascade
     */
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "level_seq")
    private Level level;

    /*
     * social은 member에 종속적이므로 cascade
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_seq")
    private Social social;

}
