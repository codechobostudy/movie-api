package io.codechobo.member.domain;


import io.codechobo.member.domain.support.SocialDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SOCIAL_SEQ", updatable = false)
    private Long seq;

    @Column(name = "SOCIAL_TYPE")
    private String type; // 소셜 타입 ex: facebook, twitter, ... etc

    @Column(name = "SOCIAL_ACCESS_TOKEN")
    private String token; // accessToken 등 token정보

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    public Social(final SocialDto socialDto) {
        this.seq = socialDto.getSequence();
        this.type = socialDto.getType();
        this.token = socialDto.getToken();
    }
}
