package io.codechobo.movie.application;

import io.codechobo._testhelper.AssertUtils;
import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.dto.MovieDto;
import io.codechobo.person.application.PersonService;
import io.codechobo.person.application.PersonServiceTest;
import io.codechobo.person.domain.Person;
import io.codechobo.person.domain.repository.PersonRepository;
import io.codechobo.person.dto.PersonDto;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
@Transactional
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;


    private Person directorFixture;
    private Person actorFixture;


    @Before
    public void tearUp() throws ParseException {
        this.directorFixture = personService.registerPerson(PersonServiceTest.generateFixture("스콧 데릭슨", DateUtils.parseDate("19660716", "yyyyMMdd")));
        this.actorFixture = personService.registerPerson(PersonServiceTest.generateFixture("베네딕트 컴버배치", DateUtils.parseDate("19760719", "yyyyMMdd")));
    }

    @Test
    public void 영화_등록_및_취득_테스트() {
        final MovieDto fixture = generateFixture();
        final Movie registered = this.movieService.registerMovie(fixture);
        final Movie retrieved = this.movieService.getMovie(registered.getNo());

        //TODO 컨버팅 유틸리티
        AssertUtils.assertEquals(retrieved, registered);
    }

    @Test
    public void 영화_수정_테스트() {
        final MovieDto fixture = generateFixture();
        final Movie registered = this.movieService.registerMovie(fixture);

        fixture.setTitle(registered.getTitle() + "_MOD");
        fixture.setGenre(registered.getGenre() + "_MOD");
        fixture.setRunningTime(100);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 8, 8, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        fixture.setReleaseDate(calendar.getTime());
        fixture.setNo(registered.getNo());
        this.movieService.modifyMovie(fixture);

        final Movie retrieved = this.movieService.getMovie(registered.getNo());

        AssertUtils.assertEquals(retrieved, registered);
    }

    @Test
    public void 영화_삭제_테스트() {
        final MovieDto fixture = generateFixture();
        final Movie registered = this.movieService.registerMovie(fixture);
        this.movieService.removeMovie(new MovieDto(registered.getNo()));
        final Movie retrieved = this.movieService.getMovie(registered.getNo());
        assertNull(retrieved);
    }


    private MovieDto generateFixture() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 8, 7, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        final PersonDto director = new PersonDto(this.directorFixture.getNo(), this.directorFixture.getName(), this.directorFixture.getDateOfBirth());
        final PersonDto actor = new PersonDto(this.actorFixture.getNo(), this.actorFixture.getName(), this.actorFixture.getDateOfBirth());
        final MovieDto dto =  new MovieDto("밀정", "첩보", 140, calendar.getTime());
        dto.addDirector(director);
        dto.addActor(actor);
        return dto;
    }

}
