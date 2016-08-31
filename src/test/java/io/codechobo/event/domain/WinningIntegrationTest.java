package io.codechobo.event.domain;

import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.domain.repository.WinningRepository;
import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static io.codechobo.event.domain.EventBuilder.anEvent;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2016-08-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class WinningIntegrationTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventCategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JoiningRepository joiningRepository;

    @Autowired
    private WinningRepository winningRepository;

    private EventBuilder eventBuilder;
    private EventCategory category;
    private Event anEvent;
    private Joining 응모1;
    private Joining 응모2;
    private Member 응모자1;
    private Member 응모자2;

    private Winning winning;

    @Before
    public void setUp() {
        응모자1 = new Member("member1", "password", "닉네임1", "이메일1@gmail.com", new Integer(0), null);
        memberRepository.save(응모자1);
        응모자2 = new Member("member2", "password", "닉네임2", "이메일2@gmail.com", new Integer(0), null);
        memberRepository.save(응모자2);

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

        응모1 = new Joining(anEvent, 응모자1);
        joiningRepository.save(응모1);

        응모2 = new Joining(anEvent, 응모자2);
        joiningRepository.save(응모2);

        winning = new Winning();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void 당첨자_추가() {
        //given
        winning.addEventJoin(응모1);
        winning.addEventJoin(응모2);

        //when
        winningRepository.save(winning);

        //then
        assertThat(winning.getEventJoins().size(), is(2));
    }
}