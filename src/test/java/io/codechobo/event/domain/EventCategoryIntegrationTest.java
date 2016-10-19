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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static io.codechobo.event.domain.EventBuilder.anEvent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Kj Nam
 * @since 2016-08-28
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = "test")
public class EventCategoryIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    private EventCategory 온라인카테고리;

    private EventBuilder eventBuilder;

    @Before
    public void setUp() {
        온라인카테고리 = new EventCategory("온라인");
        eventCategoryRepository.save(온라인카테고리);

        eventBuilder = anEvent()
                     .with이벤트명("더블예매이벤트")
                     .with설명("이벤트 설명 입니다")
                     .with시작일(new Date())
                     .with종료일(new Date())
                     .with페이지주소("/event1")
                     .with카테고리(온라인카테고리)
                     .with이벤트상태(EventStatus.OPEN);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 한_카테고리에_여러_이벤트() {
        Event 더블예매이벤트 = eventBuilder.build();
        Event 기대평이벤트 = eventBuilder.but().with이벤트명("기대평이벤트").build();

        //when
        eventRepository.save(더블예매이벤트);
        eventRepository.save(기대평이벤트);

        //then
        assertThat(온라인카테고리.getEvents().get(0).getName(), is("더블예매이벤트"));
        assertThat(온라인카테고리.getEvents().get(1).getName(), is("기대평이벤트"));
    }
}