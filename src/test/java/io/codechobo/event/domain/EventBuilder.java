package io.codechobo.event.domain;

import java.util.Date;

/**
 * @author Kj Nam
 * @since 2016-08-29
 */
public class EventBuilder {

    private Event event;

    private EventBuilder() {
        this.event = new Event();
    }

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public Event build() {
        return event;
    }

    public EventBuilder with이벤트명(String eventName) {
        this.event.setName(eventName);
        return this;
    }

    public EventBuilder with설명(String description) {
        this.event.setDescription(description);
        return this;
    }

    public EventBuilder with페이지주소(String resourceUrl) {
        this.event.setResourceUrl(resourceUrl);
        return this;
    }

    public EventBuilder with시작일(Date startDate) {
        this.event.setStartDate(startDate);
        return this;
    }

    public EventBuilder with종료일(Date endDate) {
        this.event.setEndDate(endDate);
        return this;
    }

    public EventBuilder with카테고리(EventCategory category) {
        this.event.setCategory(category);
        return this;
    }

    public EventBuilder with이벤트상태(EventStatus status) {
        this.event.setStatus(status);
        return this;
    }

    public EventBuilder but() {
        return anEvent()
                .with이벤트명(this.event.getName())
                .with설명(this.event.getDescription())
                .with페이지주소(this.event.getResourceUrl())
                .with시작일(this.event.getStartDate())
                .with종료일(this.event.getEndDate())
                .with카테고리(this.event.getCategory())
                .with이벤트상태(this.event.getStatus());
    }
}
