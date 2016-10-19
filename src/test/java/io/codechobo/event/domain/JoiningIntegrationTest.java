package io.codechobo.event.domain;

import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static io.codechobo.event.domain.EventBuilder.anEvent;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-08-31
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class JoiningIntegrationTest {

    @Autowired
    private JoiningRepository joiningRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventCategoryRepository categoryRepository;

    private Member member1;
    private Member member2;
    private EventBuilder eventBuilder;
    private EventCategory category;
    private Event anEvent;
    private Joining joining1;
    private Joining joining2;

    @Before
    public void setUp() {
        member1 = new Member(new MemberDto("member1", "password", "이메일1@gmail.com", "닉네임1"));
        member2 = new Member(new MemberDto("member2", "password", "이메일2@gmail.com", "닉네임2"));
        memberRepository.save(member1);
        memberRepository.save(member2);

        category = new EventCategory("온라인이벤트");
        categoryRepository.save(category);

        eventBuilder = anEvent()
                .with이벤트명("더블예매이벤트")
                .with설명("이벤트 설명 입니다")
                .with시작일(new Date())
                .with종료일(new Date())
                .with페이지주소("/event1")
                .with카테고리(category)
                .with이벤트상태(EventStatus.OPEN);

        anEvent = eventBuilder.build();
        eventRepository.save(anEvent);
    }

    @After
    public void tearDown() {
        joiningRepository.deleteAll();
    }

    @Test
    public void 한_이벤트_여려명_응모() {
        //given
        joining1 = new Joining(anEvent, member1, new Date());
        joining2 = new Joining(anEvent, member2, new Date());

        //when
        joiningRepository.save(joining1);
        joiningRepository.save(joining2);

        //then
        assertThat(joiningRepository.findByEventId(anEvent.getId()).size(), is(2));
    }

    @Test
    public void 한명이_여러_이벤트_응모() {
        //given
        Event 새로운이벤트 = eventBuilder.but().with이벤트명("새로운이벤트").build();
        eventRepository.save(새로운이벤트);
        joining1 = new Joining(anEvent, member1, new Date());
        joining2 = new Joining(새로운이벤트, member1, new Date());

        //when
        joiningRepository.save(joining1);
        joiningRepository.save(joining2);

        //then
        assertThat(joiningRepository.findByMemberId(member1.getId()).size(), is(2));
    }
}