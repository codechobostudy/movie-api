package io.codechobo.theater.domain.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterDto {

    private Long id;
    private String name; /* 극장 이름 */
    private String location; /* 극장 위치 */
}
