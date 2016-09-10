package io.codechobo.event.interfaces.api.support;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Kj Nam
 * @since 2016-09-05
 */
@Getter
@Setter
public class EventCategoryDto {
    private Long id;

    @NotNull
    private String name;
}
