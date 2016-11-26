package io.codechobo.domain.member;

import javax.persistence.*;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity(name = "member_social")
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "social_seq", updatable = false)
    private Integer seq;

    @Column(name = "social_type")
    private String type; // 소셜 타입 ex: facebook, twitter, ... etc

    @Column(name = "social_access_token")
    private String token; // accessToken 등 token정보

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
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
