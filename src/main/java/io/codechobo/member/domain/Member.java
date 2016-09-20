package io.codechobo.member.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SOCIAL_SEQ")
    private List<Social> socials;

    public Member(String id, String password, String nickName, String email, Integer point, ArrayList<Social> socials) {
        this.id = id;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.point = point;
        this.level = PointPerLevel.valueOf(this.point);
        this.socials = socials;
        this.registrationDate = new Date();
    }

    @PrePersist
    public void tmpLevelPersist() {
        this.level = PointPerLevel.valueOf(this.point);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        builder.append("sequence : "+this.seq)
                .append(", id : "+this.id)
                .append(", password : "+this.password)
                .append(", nickName : "+this.nickName)
                .append(", email : "+this.email)
                .append(", point : "+this.point)
                .append(", level : "+this.level)
                .append("]");

        return builder.toString();
    }
}
