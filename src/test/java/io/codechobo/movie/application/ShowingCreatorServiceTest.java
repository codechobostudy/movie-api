package io.codechobo.movie.application;

import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.repository.MovieRepository;
import io.codechobo.movie.domain.repository.ShowingRepository;
import io.codechobo.theater.domain.MovieType;
import io.codechobo.theater.domain.Screen;
import io.codechobo.theater.domain.Seat;
import io.codechobo.theater.domain.Theater;
import io.codechobo.theater.domain.repository.ScreenRepository;
import io.codechobo.theater.domain.repository.SeatRepository;
import io.codechobo.theater.domain.repository.TheaterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ShowingCreatorServiceTest {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ShowingCreatorService showingCreatorService;
    @Autowired
    ShowingRepository showingRepository;

    //TODO fixture는 공용으로 사용할 수 있도록 분리할 것
    private Screen screen;
    private Movie movie;

    @Before
    public void setup() {
        Theater theater = theaterRepository.save(new Theater("테스트이름", "테스트장소"));
        Seat seat = seatRepository.save(new Seat(new int[]{0, 1, 1, 1, 1, 0}));

        this.screen = screenRepository.save(new Screen(MovieType.TWO_D, theater, seat));
        this.movie = movieRepository.save(
                new Movie("센과 치히로의 행방불명", "애니매이션", 120, new Date()));
    }

    @Test
    public void createShowing() throws Exception {
        //given
        Movie movie = this.movie;
        Screen screen = this.screen;

        //when
        Showing showing = showingCreatorService.createShowing(movie, screen, new Date());

        //then
        Showing queriedShowing = showingRepository.findOne(showing.getId());
        assertThat(queriedShowing.getMovie().getTitle()).isEqualTo(movie.getTitle());
        assertThat(queriedShowing.getScreen().getId()).isEqualTo(screen.getId());
        assertThat(queriedShowing.getStartTime()).isNotNull();

        assertThat(queriedShowing.getShowingSeats()).hasSize(6);
    }
}