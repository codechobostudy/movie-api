package io.codechobo.event.domain;

import io.codechobo.event.interfaces.api.support.WinningDto;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 이벤트 당첨 엔티티
 *
 * @author Kj Nam
 * @since 2016-08-06
 */
@Entity
@Getter
public class Winning {

    @Id @GeneratedValue
    @Column(name = "WIN_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @Temporal(TemporalType.DATE)
    private Date winningDate;

    @OneToMany(mappedBy = "winning")
    private List<Joining> joins = new ArrayList<>();


    protected Winning() {
    }

    public Winning(Event event, Joining joining, Date winningDate) {
        this.event = event;
//        this.eventJoins.add(joining);
        this.winningDate = winningDate;
    }

    public Winning(WinningDto winningDto) {
        this.id = winningDto.getId();
        this.winningDate = winningDto.getWinningDate();
    }

    public void addJoins(Joining joining) {
        this.joins.add(joining);
        if (joining.getWinning() != this) {
            joining.setWinning(this);
        }
    }

    public void setEvent(Event event) {
        this.event = event;
        if (!event.getEventJoins().contains(this)) {
            event.getEventWins().add(this);
        }
    }
}
