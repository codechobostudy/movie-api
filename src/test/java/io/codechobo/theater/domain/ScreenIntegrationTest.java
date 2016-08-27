package io.codechobo.theater.domain;

import io.codechobo.MovieApplication;
import io.codechobo.theater.domain.repository.ScreenRepository;
import io.codechobo.theater.domain.repository.SeatRepository;
import io.codechobo.theater.domain.repository.TheaterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class ScreenIntegrationTest {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenRepository screenRepository;

    private Theater saveTheater;
    private Seat saveSeat;

    @Before
    public void setUp() {
        saveTheater = createTheaterFixture();
        saveSeat = createSeatFixture();
    }

    @Test
    public void 테스트설정_확인_테스트_Screen_Integration_Test() {
    }

    @Test
    public void 스크린정보_등록시_극장정보_좌석정보_등록() {

        //given
        Screen screen = new Screen(MovieType.IMAX, saveTheater, saveSeat);

        //when
        Screen saveScreen = screenRepository.save(screen);

        //then
        assertNotNull(saveScreen.getId());
        assertThat(saveScreen.getTheater().getName(), is("name"));
        assertThat(saveScreen.getSeat().getSeats().length, is(6));
    }

    //TODO : Fixture 생산팩토리 추가
    private Theater createTheaterFixture() {
        Theater theater = new Theater("name", "location");
        return theaterRepository.save(theater);
    }

    private Seat createSeatFixture() {
        int[] seats = {0, 1, 1, 1, 1, 0};
        Seat seat = new Seat(seats);
        return seatRepository.save(seat);
    }
}
