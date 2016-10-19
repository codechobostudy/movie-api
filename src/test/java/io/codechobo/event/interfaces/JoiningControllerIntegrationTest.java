package io.codechobo.event.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.EventBuilder;
import io.codechobo.event.domain.EventStatus;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.domain.repository.JoiningRepository;
import io.codechobo.event.interfaces.api.support.JoiningDto;
import io.codechobo.member.domain.Member;
import io.codechobo.member.domain.repository.MemberRepository;
import io.codechobo.member.domain.support.MemberDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static io.codechobo.event.domain.EventBuilder.anEvent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kj Nam
 * @since 2016-09-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class JoiningControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private EventRepository eventRepository;

    private Event anEvent;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JoiningRepository joiningRepository;

    private EventBuilder eventBuilder;
    private Member aMember;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        eventBuilder = anEvent()
                .with이벤트명("더블예매이벤트")
                .with설명("이벤트 설명 입니다")
                .with시작일(new Date())
                .with종료일(new Date())
                .with페이지주소("/event1")
                .with이벤트상태(EventStatus.OPEN);
        anEvent  = eventBuilder.build();
        eventRepository.save(anEvent);

        aMember = new Member(new MemberDto("member", "password", "email@gmail.com", "nickname"));
        aMember = memberRepository.save(aMember);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void 이벤트_응모_등록_201() throws Exception {
        //given
        JoiningDto joiningDto = new JoiningDto();
        joiningDto.setJoinDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(joiningDto);

        //when then
        mockMvc.perform(post("/api/joins/events/1/members/" + aMember.getSeq())
                .content(jsonContents)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}