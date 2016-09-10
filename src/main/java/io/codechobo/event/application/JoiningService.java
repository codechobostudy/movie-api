package io.codechobo.event.application;

import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.Joining;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.interfaces.api.support.JoiningDto;
import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kj Nam
 * @since 2016-09-08
 */
@Service
public class JoiningService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JoiningRepository joiningRepository;

    @Transactional
    public Joining save(Long eventId, Long memberSeq, JoiningDto joiningDto) {
        Event event = eventRepository.findOne(eventId);
        Member member = memberRepository.findOne(memberSeq);
        Joining joining = new Joining(event, member, joiningDto);
        event.addEventJoin(joining);

        return joining;
    }
}
