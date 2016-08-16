package io.codechobo.domain.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;

/**
 * Created by Loustler on 8/7/16.
 */
@Entity
public class Level {
    /*
     * 레벨과 멤버는 1:1 관계, FK역할을 해야 하는데 어떻게?
     * member에 Level을 ? Level에 member를? Level은 member에 종속적? 레벨이 없는 멤버는 없다 멤버가 없는 레벨은?
     * 도메인 한개에 다 넣기에는 ... 부적절한 것으로 보임
     * sequence에 대하여 update는 못하게 막음
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LEVEL_SEQ", updatable = false)
    private Integer seq;

    @Column(name = "LEVEL_POINT")
    private Integer point; // 유저가 적립한 포인트

    /**
     * 유저 등급
     * Wiki 참조(https://github.com/codechobostudy/movie-api/wiki/회원-CRC)
     * 등급 4가지로 분류
     * basic, standard, vip, vvip
     * 포인터별로 산정하며 등급에 대한 포인트점수는 미정
     * 임시 산정으로 preUpdateLevel() 에 임시로 배정
     */
    @Column(name = "LEVEL_LEVEL")
    @Enumerated(EnumType.STRING)
    private PointPerLevel level;

    /**
     * level에 존재하려면 member가 있어야 하므로 member와 상호 association
     */
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    @PreUpdate
    public void preUpdateLevel() {
        /**
         * Enum으로 구현
         * PointPerLevel Enum Class에 구현됨
         */
        if(point < 100) level = PointPerLevel.BASIC;
        else if(point >= 100 && point < 200) level = PointPerLevel.STANDARD;
        else if(point >= 200 && point < 500) level = PointPerLevel.VIP;
        else level = PointPerLevel.VVIP;
    }

    public Level() {}
    public Level(Integer point) {
        this.point = point;
        preUpdateLevel();
    }

    public void setPoint(Integer point) {this.point = point;}

    public PointPerLevel getLevel() {return this.level;}
    public Integer getPoint() {return this.point;}

}
