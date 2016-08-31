package io.codechobo.event.domain;

import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.domain.repository.EventRepository;
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
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2016-08-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class EventIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    private EventCategory onlineCategory;
    private EventBuilder eventBuilder;
    private Event anEvent;

    @Before
    public void setUp() {
        onlineCategory = new EventCategory("온라인");
        eventCategoryRepository.save(onlineCategory);

        eventBuilder = anEvent()
                    .with이벤트명("더블예매이벤트")
                    .with설명("이벤트 설명 입니다")
                    .with시작일(new Date())
                    .with종료일(new Date())
                    .with페이지주소("/event1")
                    .with카테고리(onlineCategory)
                    .with이벤트상태(EventStatus.OPEN);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 이벤트_생성() {
        //given
        anEvent = eventBuilder.build();

        //when
        eventRepository.save(anEvent);

        //then
        assertThat(eventRepository.findAll().size(), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void 이벤트_생성간_존재하지_않는_카테고리_참조시_예외() {
        //given
        onlineCategory = null;
        anEvent = eventBuilder.but().with카테고리(onlineCategory).build();

        //when
        eventRepository.save(anEvent);

        //then
        // exception
    }
}