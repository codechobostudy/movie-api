package io.codechobo.event.interfaces.api.support;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Kj Nam
 * @since 2016-09-17
 */
@Setter
@Getter
public class WinningDto {
    private Long id;

    private Date winningDate;

    public WinningDto() {
    }

    public WinningDto(Long id) {
        this.id = id;
    }

    public WinningDto(Date winningDate) {
        this.winningDate = winningDate;
    }

    public WinningDto(Long id, Date winningDate) {
        this.id = id;
        this.winningDate = winningDate;
    }
}
