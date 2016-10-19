package io.codechobo.event.interfaces.api.support;

import io.codechobo.event.domain.EventCategory;
import io.codechobo.event.domain.EventStatus;
import io.codechobo.event.domain.Joining;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-09-04
 */
@Getter
@Setter
public class EventDto {
    private Long id;

    @NotNull
    private String name;

    private String resourceUrl;

    private EventCategory category;

    private String description;

    private List<Joining> eventJoins;

    private EventStatus status;

    private Date startDate;

    private Date endDate;
}
