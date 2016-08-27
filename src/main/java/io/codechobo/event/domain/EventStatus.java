package io.codechobo.event.domain;

import lombok.Getter;

/**
 * 이벤트 상태
 *
 * @author Kj Nam
 * @since 2016-08-06
 */
@Getter
public enum EventStatus {
    OPEN,  // 진행중
    CLOSED // 종료
}
