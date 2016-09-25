package io.codechobo.movie.application;

import io.codechobo.movie.dto.MovieDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void 영화_등록_및_취득_테스트() {
        final MovieDto fixture = generateFixture();
        final MovieDto registered = this.movieService.registerMovie(fixture);
        final MovieDto retrieved = this.movieService.getMovie(registered.getNo());

        assertNotNull(registered.getNo());
        assertThat(registered.getNo(), is(retrieved.getNo()));
        assertThat(registered.getTitle(), is(retrieved.getTitle()));
        assertThat(registered.getGenre(), is(retrieved.getGenre()));
        assertThat(registered.getRunningTime(), is(retrieved.getRunningTime()));
        assertThat(registered.getReleaseDate().getTime(), is(retrieved.getReleaseDate().getTime()));
    }

    @Test
    public void 영화_수정_테스트() {
        final MovieDto fixture = generateFixture();
        final MovieDto registered = this.movieService.registerMovie(fixture);

        registered.setTitle(registered.getTitle()+"_MOD");
        registered.setGenre(registered.getGenre()+"_MOD");
        registered.setRunningTime(100);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 8, 8, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        registered.setReleaseDate(calendar.getTime());
        final MovieDto modified = this.movieService.modifyMovie(registered);

        final MovieDto retrieved = this.movieService.getMovie(registered.getNo());

        assertNotNull(modified.getNo());
        assertThat(modified.getNo(), is(retrieved.getNo()));
        assertThat(modified.getTitle(), is(retrieved.getTitle()));
        assertThat(modified.getGenre(), is(retrieved.getGenre()));
        assertThat(modified.getRunningTime(), is(retrieved.getRunningTime()));
        assertThat(modified.getReleaseDate().getTime(), is(retrieved.getReleaseDate().getTime()));
    }

    @Test
    public void 영화_삭제_테스트() {
        final MovieDto fixture = generateFixture();
        final MovieDto registered = this.movieService.registerMovie(fixture);
        this.movieService.removeMovie(new MovieDto(registered.getNo()));
        final MovieDto retrieved = this.movieService.getMovie(registered.getNo());
        assertNull(retrieved);
    }


    private MovieDto generateFixture() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 8, 7, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new MovieDto("밀정", "첩보", 140, calendar.getTime());
    }

}
