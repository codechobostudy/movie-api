package io.codechobo.theater.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Seat {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private int[] seats; // ?

    public Seat(int[] seats) {
        this.seats = seats;
    }
}
