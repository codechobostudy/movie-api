package io.codechobo.event.application;

import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.Joining;
import io.codechobo.event.domain.Winning;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.domain.repository.WinningRepository;
import io.codechobo.event.interfaces.api.support.WinningDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kj Nam
 * @since 2016-10-03
 */
@Service
public class WinningService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    WinningRepository winningRepository;

    @Autowired
    JoiningRepository joiningRepository;

    @Transactional
    public Winning save(Long eventId, Long joiningId, WinningDto winningDto) {
        Event event = eventRepository.findOne(eventId);
        Joining joining = joiningRepository.findOne(joiningId);
        Winning winning = new Winning(winningDto);
        winning.addJoins(joining);
        event.addEventWins(winning);

        return winning;
    }
}
