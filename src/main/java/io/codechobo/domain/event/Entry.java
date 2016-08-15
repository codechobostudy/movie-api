package io.codechobo.domain.event;

import io.codechobo.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Entry {

    @Id @GeneratedValue
    @Column(name = "ENTRY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "WIN_ID", insertable = false, updatable = false)
    private Winning winning;

    public Entry(Event event, Member member) {
        this.event = event;
        this.member = member;
    }

    public void setEvent(Event event) {
        this.event = event;
        if (!event.getEventEntries().contains(this)) {
            event.getEventEntries().add(this);
        }
    }

    public void setWinning(Winning winning) {
        this.winning = winning;
        if (!winning.getEntries().contains(this)) {
            winning.getEntries().add(this);
        }
    }
}
