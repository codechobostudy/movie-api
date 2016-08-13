package io.codechobo.domain;

import javax.persistence.*;


/**
 * Created by Loustler on 8/7/16.
 */
@Entity(name = "member_level")
public class Level {
    /*
     * 레벨과 멤버는 1:1 관계, FK역할을 해야 하는데 어떻게?
     * member에 Level을 ? Level에 member를? Level은 member에 종속적? 레벨이 없는 멤버는 없다 멤버가 없는 레벨은?
     * 도메인 한개에 다 넣기에는 ... 부적절한 것으로 보임
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "level_seq", insertable = false, updatable = false)
    private Integer seq;

    @Column(name = "level_point")
    private Integer point; // 유저가 적립한 포인트

    /*
     * 유저 등급
     * Wiki 참조(https://github.com/codechobostudy/movie-api/wiki/회원-CRC)
     * 등급 4가지로 분류
     * basic, standard, vip, vvip
     * 포인터별로 산정하며 등급에 대한 포인트점수는 미정
     */
    @Column(name = "level_level")
    private String level;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member;

    @PrePersist
    public String getLevel() {
        /*
         * Level 가져올 때 레벨에 포인트 산정해서 레벨 매기기
         * Enum? If?
         */
        if(this.point < 100) this.level = "basic";
        else if(this.point >= 100 && this.point < 200) this.level = "standard";
        else if(this.point >= 200 && this.point < 500) this.level = "vip";
        else this.level = "vvip";

        return level;
    }

    public Integer getPoint() {
        return point;
    }

}
