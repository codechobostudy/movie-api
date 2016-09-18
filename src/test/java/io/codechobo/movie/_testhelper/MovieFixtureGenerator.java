package io.codechobo.movie._testhelper;

import io.codechobo.movie.application.ShowingCreatorService;
import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.repository.MovieRepository;
import io.codechobo.theater.domain.MovieType;
import io.codechobo.theater.domain.Screen;
import io.codechobo.theater.domain.Seat;
import io.codechobo.theater.domain.Theater;
import io.codechobo.theater.domain.repository.ScreenRepository;
import io.codechobo.theater.domain.repository.SeatRepository;
import io.codechobo.theater.domain.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MovieFixtureGenerator {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowingCreatorService creatorService;

    public Screen createFixtureScreen() {
        Theater theater = theaterRepository.save(new Theater("테스트이름", "테스트장소"));
        Seat seat = seatRepository.save(new Seat(new int[]{0, 1, 1, 1, 1, 0}));

        return screenRepository.save(new Screen(MovieType.TWO_D, theater, seat));
    }

    public Movie createFixtureMovie() {
        return movieRepository.save(new Movie("센과 치히로의 행방불명", "애니매이션", 120, new Date()));
    }

    public Showing createFixtureShowing(Movie movie, Screen screen) {
        return creatorService.createShowing(movie, screen, new Date());
    }

    public Showing createFixtureShowing() {
        return creatorService.createShowing(
                this.createFixtureMovie(),
                this.createFixtureScreen(),
                new Date()
        );
    }
}
