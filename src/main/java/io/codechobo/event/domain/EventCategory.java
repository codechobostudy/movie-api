package io.codechobo.event.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 이벤트 카테고리 엔티티
 *
 * @author Kj Nam
 * @since 2016-08-06
 */
@Entity
@Getter
public class EventCategory {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    protected EventCategory() {
    }

    public EventCategory(String name) {
        this.name = name;
    }

    public void addEvent(Event event) {
        this.events.add(event);
        if (event.getCategory() != this) {
            event.setCategory(this);
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
