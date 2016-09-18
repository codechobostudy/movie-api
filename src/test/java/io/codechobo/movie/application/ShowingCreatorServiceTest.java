package io.codechobo.movie.application;

import io.codechobo.movie._testhelper.MovieFixtureGenerator;
import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.repository.ShowingRepository;
import io.codechobo.theater.domain.Screen;
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
    MovieFixtureGenerator movieFixtureGenerator;
    @Autowired
    ShowingCreatorService showingCreatorService;
    @Autowired
    ShowingRepository showingRepository;


    @Test
    public void createShowing() throws Exception {
        //given
        Movie movie = movieFixtureGenerator.createFixtureMovie();
        Screen screen = movieFixtureGenerator.createFixtureScreen();

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