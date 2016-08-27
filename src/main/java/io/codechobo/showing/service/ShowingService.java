package io.codechobo.showing.service;

import io.codechobo.showing.dto.ShowingCreateRequest;
import io.codechobo.showing.infra.ShowingRepository;
import io.codechobo.showing.infra.ShowingSeatRepository;
import io.codechobo.showing.model.Showing;
import io.codechobo.showing.model.ShowingSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShowingService {
    @Autowired
    ShowingRepository showingRepo;
    @Autowired
    ShowingSeatRepository showingSeatRepo;

    @Transactional
    public Showing create(ShowingCreateRequest request) {
        Showing showing = showingRepo.save(new Showing(request.getMovieName()));

        for (int idx = 0; idx < request.getAvailableSeat(); idx++) {
            showingSeatRepo.save(new ShowingSeat(showing));
        }

        return showing;
    }
}
