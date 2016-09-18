package io.codechobo.movie.application;

import io.codechobo.movie.domain.Showing;
import io.codechobo.movie.domain.ShowingSeat;
import io.codechobo.movie.domain.repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.codechobo.movie.domain.ShowingSeat.ShowingSeatState.OCCUPANCY;

/**
 * 회원은 티켓을 예매할 수 있다.
 */
@Service
public class TicketingService {

    @Autowired
    ShowingRepository showingRepository;

    public List<ShowingSeat> getBookAvailableSeats(Long id) {
        Showing showing = showingRepository.getOne(id);

        return showing.getShowingSeats().stream()
                .filter(s -> s.getState() != OCCUPANCY)
                .collect(Collectors.toList());
    }
}
