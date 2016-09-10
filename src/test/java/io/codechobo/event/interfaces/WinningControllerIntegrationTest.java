package io.codechobo.event.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.event.application.WinningService;
import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.EventCategory;
import io.codechobo.event.domain.EventStatus;
import io.codechobo.event.domain.Joining;
import io.codechobo.event.domain.Winning;
import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.domain.repository.WinningRepository;
import io.codechobo.event.interfaces.api.support.WinningDto;
import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.codechobo.event.domain.EventBuilder.anEvent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Kj Nam
 * @since 2016-09-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class WinningControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WinningRepository winningRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JoiningRepository joiningRepository;

    @Autowired
    private EventCategoryRepository categoryRepository;

    @MockBean
    private WinningService winningService;

    private Event anEvent;
    private Member 응모자1;
    private Member 응모자2;
    private Joining joining;
    private EventCategory category;

    List<Joining> 당첨자1명리스트 = new ArrayList<>();
    List<Joining> 당첨자2명리스트 = new ArrayList<>();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        category = new EventCategory("온라인이벤트");
        categoryRepository.save(category);

        anEvent = anEvent()
                .with이벤트명("더블예매이벤트")
                .with설명("이벤트 설명 입니다")
                .with시작일(new Date())
                .with종료일(new Date())
                .with페이지주소("/event1")
                .with이벤트상태(EventStatus.OPEN).build();
        anEvent = eventRepository.save(anEvent);

        응모자1 = new Member(new MemberDto("member1", "password1", "email1@gmail.com", "nickname1"));
        응모자2 = new Member(new MemberDto("member2", "password2", "email2@gmail.com", "nickname2"));
        memberRepository.save(응모자1);
        memberRepository.save(응모자2);

        joining = new Joining(anEvent, 응모자1, new Date());
        joining = joiningRepository.save(joining);
        당첨자1명리스트.add(joining);
        당첨자2명리스트.add(joining);
        joining = new Joining(anEvent, 응모자2, new Date());
        joining = joiningRepository.save(joining);
        당첨자2명리스트.add(joining);
    }

    @After
    public void tearDown() {
        winningRepository.deleteAll();
        joiningRepository.deleteAll();
        eventRepository.deleteAll();
        categoryRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void 새_당첨자_추첨_201() throws Exception {
        //given
        WinningDto winningDto = new WinningDto(new Date());
        objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(winningDto);

        mockMvc.perform(post("/api/wins/events/" + anEvent.getId() + "/joins/" + joining.getId())
                .content(jsonContents)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //when

        //then
    }

    @Test
    public void 당첨자_리스트_조회() throws Exception {
        //given
        WinningDto winningDto = new WinningDto(new Date());
        Winning winning = new Winning(winningDto);
        winning.addJoins(joining);
        winningRepository.save(winning);

        //when
        mockMvc.perform(get("/api/wins/events/" + anEvent.getId()))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
    }
}