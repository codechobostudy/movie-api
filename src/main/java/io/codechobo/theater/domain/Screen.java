package io.codechobo.theater.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private MovieType availableMovieType;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToOne //LAZY?
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Screen(MovieType availableMovieType, Theater theater, Seat seat) {
        this.availableMovieType = availableMovieType;
        this.theater = theater;
        this.seat = seat;
    }
}
