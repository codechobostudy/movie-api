package io.codechobo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity(name = "member_social")
@Getter
@Setter
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

}
