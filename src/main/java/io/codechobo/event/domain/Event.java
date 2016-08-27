package io.codechobo.event.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * 이벤트 엔티티
 *
 * @author Kj Nam
 * @since 2016-08-05
 */
@Entity
@Getter
public class Event {

    @Id @GeneratedValue
    @Column(name = "EVENT_ID")
    private Long id;

    private String name;

    private String description;

    @Column(name = "RESOURCE_URL")
    private String resourceUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private EventCategory category;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<Joining> eventJoins = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    protected Event() {
    }

    public Event(String name, String description, String resourceUrl,
                 EventCategory category, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.resourceUrl = resourceUrl;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addEventJoin(Joining joining) {
        this.eventJoins.add(joining);
        if (joining.getEvent() != this) {
            joining.setEvent(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
        if (!category.getEvents().contains(this)) {
            category.getEvents().add(this);
        }
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
