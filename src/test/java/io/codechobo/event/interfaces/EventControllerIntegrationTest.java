package io.codechobo.event.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.event.domain.Event;
import io.codechobo.event.domain.EventBuilder;
import io.codechobo.event.domain.EventStatus;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.interfaces.api.support.EventDto;
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

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kj Nam
 * @since 2016-08-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class EventControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    EventRepository eventRepository;
    private Event anEvent;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void 새_이벤트_등록_201() throws Exception {
        //given
        EventDto dto = new EventDto();
        dto.setName("추첨이벤트");
        dto.setDescription("추첨이벤트 설명");
        dto.setResourceUrl("/event1");
        dto.setStartDate(new Date());
        dto.setEndDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(dto);

        //when then
        mockMvc.perform(post("/api/events")
            .content(jsonContents)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated());
    }

    @Test
    public void 이름_없는_이벤트_등록시_400() throws Exception {
        //given
        EventDto dto = new EventDto();
        // dto.setName("");
        dto.setDescription("추첨이벤트 설명");
        dto.setResourceUrl("/event1");
        dto.setStartDate(new Date());
        dto.setEndDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(dto);

        //when then
        mockMvc.perform(post("/api/events")
                .content(jsonContents).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 이벤트_리스트_조회_200() throws Exception {
        //given

        //when then
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk());
    }

    @Test
    public void 이벤트_상세_조회_200() throws Exception {
        //given
        anEvent = eventRepository.save(EventBuilder.anEvent()
                            .with이벤트명("더블예매이벤트")
                            .with설명("이벤트 설명 입니다")
                            .with시작일(new Date())
                            .with종료일(new Date())
                            .with페이지주소("/event1")
                            .with이벤트상태(EventStatus.OPEN).build());
        eventRepository.save(anEvent);

        //when then
        mockMvc.perform(get("/api/events/" + anEvent.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 이벤트_정보_수정_200() throws Exception {
        //given
        anEvent = eventRepository.save(EventBuilder.anEvent()
                .with이벤트명("더블예매이벤트")
                .with설명("이벤트 설명 입니다")
                .with시작일(new Date())
                .with종료일(new Date())
                .with페이지주소("/event1")
                .with이벤트상태(EventStatus.OPEN).build());
        eventRepository.save(anEvent);

        EventDto eventDto = new EventDto();
        eventDto.setId(anEvent.getId());
        eventDto.setName("수정된예매이벤트");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(eventDto);

        //when then
        String body = mockMvc.perform(put("/api/events/" + anEvent.getId())
                .content(jsonContents)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(body, containsString(eventDto.getName()));
    }

    @Test
    public void 잘못된_요청_400() throws Exception {
        //given

        //when then
        mockMvc.perform(get("/api/events/abcdef"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 이벤트_정보_삭제_200() throws Exception {
        anEvent = eventRepository.save(EventBuilder.anEvent()
                .with이벤트명("더블예매이벤트")
                .with설명("이벤트 설명 입니다")
                .with시작일(new Date())
                .with종료일(new Date())
                .with페이지주소("/event1")
                .with이벤트상태(EventStatus.OPEN).build());
        eventRepository.save(anEvent);

        //when then
        mockMvc.perform(delete("/api/events/" + anEvent.getId()))
                .andExpect(status().isOk());
    }
}