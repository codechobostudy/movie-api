package io.codechobo.domain.member;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_seq", updatable = false)
    private Integer seq; // 시퀀스

    @Column(name = "member_id", unique = true)
    private String id; // Id Uniq

    @Column(name = "member_password")
    private String pwd; // 패스워드

    @Column(name = "member_nickname")
    private String nickName; // 닉네임 uqniq

    @Column(name = "member_email", unique = true)
    private String email; // 이메일 uniq

    /*
     * 회원가입일은 변경될 일이 없기때문에 update 못하게
     */
    @Column(name = "member_reg_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate; // 회원가입일

    /*
     * Join시 level은 반드시 member에게 존재하여야 함(optional false로, false시 not-null)
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

    public Member(){}

    public Member(String id, String pwd, String nickName, String email, Level level, Social social) {
        this.id = id;
        this.pwd = pwd;
        this.nickName = nickName;
        this.email = email;
        this.level = level;
        this.social = social;
        this.regDate = new Date();
    }

    public void setId(String id) {this.id = id;}
    public void setPwd(String pwd) {this.pwd = pwd;}
    public void setNickName(String nickName) {this.nickName = nickName;}
    public void setEmail(String email) {this.email = email;}
    public void setLevel(Level level) {this.level = level;}
    public void setSocial(Social social) {this.social = social;}

    public String getId() {return this.id;}
    public String getPwd() {return this.pwd;}
    public String getNickName() {return this.nickName;}
    public String getEmail() {return this.email;}
    public Level getLevel() {return this.level;}
    public Social getSocial() {return this.social;}


}
