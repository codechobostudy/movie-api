package io.codechobo.event.interfaces.api.support;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Kj Nam
 * @since 2016-09-08
 */
@Getter
@Setter
public class JoiningDto {
    Long id;
    Date joinDate;
}
