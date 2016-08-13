package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.*;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
@Getter
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "social_seq", insertable = false, updatable = false)
    private Integer seq;

    @Column(name = "social_type")
    private String type; // 소셜 타입 ex: facebook, twitter, ... etc

    @Column(name = "social_access_token")
    private String token; // accessToken 등 token정보

    @OneToOne(optional = false)
    @JoinColumn(name = "member_seq")
    private Member member;

}
