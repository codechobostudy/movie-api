package io.codechobo.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowingCreateRequest {
    private String movieName;
    private int availableSeat;
}
