package io.codechobo.domain.member;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SOCIAL_SEQ", updatable = false)
    private Integer seq;

    @Column(name = "SOCIAL_TYPE")
    private String type; // 소셜 타입 ex: facebook, twitter, ... etc

    @Column(name = "SOCIAL_ACCESS_TOKEN")
    private String token; // accessToken 등 token정보

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    public Social() {}
    public Social(Member member, String type, String token) {
        this.member = member;
        this.type = type;
        this.token = token;
    }

    public void setType(String type) {this.type = type;}
    public void setToken(String token) {this.token = token;}
    public void setMember(Member member) {this.member = member;}

    public String getType() {return this.type;}
    public String getToken() {return this.token;}
    public Member getMember() {return this.member;}

}
