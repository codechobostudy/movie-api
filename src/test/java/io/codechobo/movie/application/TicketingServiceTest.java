package io.codechobo.movie.application;

import io.codechobo.movie._testhelper.MovieFixtureGenerator;
import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.ShowingSeat;
import io.codechobo.movie.domain.ShowingSeat.ShowingSeatState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TicketingServiceTest {

    @Autowired
    MovieFixtureGenerator fixtureGenerator;
    @Autowired
    TicketingService ticketingService;

    @Test
    public void 예매가능좌석조회() {
        //given
        Showing showing = fixtureGenerator.createFixtureShowing();

        //when
        List<ShowingSeat> ticketableSeats = ticketingService.getBookAvailableSeats(showing.getId());

        // then
        int initSeatSize = showing.getShowingSeats().size();
        assertThat(ticketableSeats).hasSize(initSeatSize);
    }

    @Test
    public void 예매하기테스트() {
        //given
        Showing showing = fixtureGenerator.createFixtureShowing();

        //when
        List<ShowingSeat> bookAvailableSeats = ticketingService.getBookAvailableSeats(showing.getId());
        bookAvailableSeats.get(0).book(0L);
        bookAvailableSeats.get(1).book(0L);

        // then
        // 0번째 상영좌석상태가 정상적으로 변경되었는지 확인한다.
        assertThat(bookAvailableSeats.get(0).getMemberId()).isEqualTo(0L);
        assertThat(bookAvailableSeats.get(0).getState()).isEqualTo(ShowingSeatState.BOOKED);
        // 1번째 상영좌석상태가 정상적으로 변경되었는지 확인한다.
        assertThat(bookAvailableSeats.get(1).getMemberId()).isEqualTo(0L);
        assertThat(bookAvailableSeats.get(1).getState()).isEqualTo(ShowingSeatState.BOOKED);

        // 상영가능한 좌석의 갯수가 정상적으러 반영되었는지 확인한다.
        int initSeatSize = showing.getShowingSeats().size();
        assertThat(bookAvailableSeats).hasSize(initSeatSize - 2);
    }

    @Test
    public void 예매취소테스트() {
        //given
        Showing showing = fixtureGenerator.createFixtureShowing();
        List<ShowingSeat> bookAvailableSeats = ticketingService.getBookAvailableSeats(showing.getId());
        bookAvailableSeats.get(0).book(0L);
        bookAvailableSeats.get(1).book(0L);

        //when
        bookAvailableSeats.get(0).cancel(0L);

        // then
        assertThat(bookAvailableSeats.get(0).getMemberId()).isNull();
        assertThat(bookAvailableSeats.get(0).getState()).isEqualTo(ShowingSeatState.OCCUPANCY);

        // 상영가능한 좌석의 갯수가 정상적으러 반영되었는지 확인한다.
        int initSeatSize = showing.getShowingSeats().size();
        assertThat(bookAvailableSeats).hasSize(initSeatSize - 1);
    }
}