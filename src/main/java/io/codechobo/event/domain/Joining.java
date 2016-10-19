package io.codechobo.event.domain;

import io.codechobo.event.interfaces.api.support.JoiningDto;
import io.codechobo.member.domain.Member;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 이벤트응모 엔티티
 *
 * @author Kj Nam
 * @since 2016-08-06
 */
@Entity
@Getter
public class Joining {

    @Id @GeneratedValue
    @Column(name = "JOIN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WIN_ID")
    private Winning winning;

    protected Joining() {
    }

    public Joining(Event event, Member member, Date joinDate) {
        this.member = member;
        this.event = event;
        this.joinDate = joinDate;
    }

    public Joining(Event event, Member member, JoiningDto joiningDto) {
        this.id = joiningDto.getId();
        this.joinDate = joiningDto.getJoinDate();
        this.event = event;
        this.member = member;
    }

    public void setEvent(Event event) {
        this.event = event;
        if (!event.getEventJoins().contains(this)) {
            event.getEventJoins().add(this);
        }
    }

    public void setWinning(Winning winning) {
        this.winning = winning;
        if (!winning.getJoins().contains(this)) {
            winning.getJoins().add(this);
        }
    }
}
