package io.codechobo.movie.application;

import io.codechobo.movie.domain.Movie;
import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.ShowingSeat;
import io.codechobo.movie.domain.repository.ShowingRepository;
import io.codechobo.movie.domain.repository.ShowingSeatRepository;
import io.codechobo.theater.domain.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.IntStream;

@Service
public class ShowingCreatorService {
    @Autowired
    ShowingRepository showingRepository;
    @Autowired
    ShowingSeatRepository showingSeatRepository;

    @Transactional
    public Showing createShowing(Movie movie, Screen screen, Date startTime) {
        Showing showing = showingRepository.save(
                new Showing(movie, screen, startTime));

        //Seat는 열과행을 가지도록 수정 필요.
        //우선은 seat의 갯수대로 showing이 생성되도록 설정
        int seatSize = screen.getSeat().getSeats().length;
        IntStream.range(0, seatSize)
                .mapToObj(i -> new ShowingSeat(showing))
                .forEach(seat -> showingSeatRepository.save(seat));

        return showing;
    }
}
