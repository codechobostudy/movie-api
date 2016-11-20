package io.codechobo.member.domain;


import io.codechobo.member.domain.support.SocialDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    /**
     * Transactional 범위밖에서 벗어나서 LAZY에서 EAGER(default)로 수정
     * Why? List일 때 proxy initialize fail
     * @see io.codechobo.member.domain.util.EntityDtoConverter#socialListConvertToDtoList(List)
     *
     * FK update 못하게 false 처리
     */
    @ManyToOne
    @JoinColumn(name = "MEMBER_SEQ", updatable = false)
    private Member member;

    public Social(final SocialDto socialDto) {
        this.seq = socialDto.getSequence();
        this.type = socialDto.getType();
        this.token = socialDto.getToken();
    }
}
