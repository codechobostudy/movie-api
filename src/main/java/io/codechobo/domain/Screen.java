package io.codechobo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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

    private ScreenType screenType;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToOne //LAZY?
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Screen(ScreenType screenType, Theater theater) {
        this.screenType = screenType;
        this.theater = theater;
    }

    public Screen(ScreenType screenType, Theater theater, Seat seat) {
        this.screenType = screenType;
        this.theater = theater;
        this.seat = seat;
    }
}
