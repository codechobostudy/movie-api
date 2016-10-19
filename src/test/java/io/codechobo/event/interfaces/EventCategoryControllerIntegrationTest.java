package io.codechobo.event.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.event.domain.EventBuilder;
import io.codechobo.event.domain.EventCategory;
import io.codechobo.event.domain.repository.EventCategoryRepository;
import io.codechobo.event.domain.repository.EventRepository;
import io.codechobo.event.interfaces.api.support.EventCategoryDto;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Kj Nam
 * @since 2016-09-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class EventCategoryControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    private EventBuilder eventBuilder;

    @Autowired
    private EventRepository eventRepository;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @After
    public void tearDown() {
        eventRepository.deleteAll();
        eventCategoryRepository.deleteAll();
    }

    @Test
    public void 이벤트_카테고리_등록_201() throws Exception {
        //given
        EventCategoryDto eventCategoryDto = new EventCategoryDto();
        eventCategoryDto.setName("온라인이벤트");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(eventCategoryDto);

        //when then
        mockMvc.perform(post("/api/eventcategories")
                .content(jsonContents)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void 이벤트_카테고리_상세조회_200() throws Exception {
        //given
        EventCategoryDto eventCategoryDto = new EventCategoryDto();
        eventCategoryDto.setName("온라인이벤트");
        EventCategory eventCategory = eventCategoryRepository.save(new EventCategory(eventCategoryDto));
        assertThat(eventCategoryRepository.findAll().size(), is(1));

        //when then
        String body = mockMvc.perform(get("/api/eventcategories/" + eventCategory.getId()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(body, is("{\"id\":" + eventCategory.getId() + ",\"name\":\"온라인이벤트\",\"events\":[]}"));
    }

    @Test
    public void 이벤트_카테고리_수정_200() throws Exception {
        //given
        EventCategoryDto eventCategoryDto = new EventCategoryDto();
        eventCategoryDto.setName("온라인이벤트");
        EventCategory eventCategory = eventCategoryRepository.save(new EventCategory(eventCategoryDto));
        assertThat(eventCategoryRepository.findAll().size(), is(1));

        EventCategoryDto editCategoryDto = new EventCategoryDto();
        editCategoryDto.setId(eventCategory.getId());
        editCategoryDto.setName("수정됨");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContents = objectMapper.writeValueAsString(editCategoryDto);

        //when then
        mockMvc.perform(put("/api/eventcategories/" + eventCategory.getId())
                .content(jsonContents)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void 이벤트_카테고리_삭제_200() throws Exception {
        //given
        EventCategoryDto eventCategoryDto = new EventCategoryDto();
        eventCategoryDto.setName("온라인이벤트");
        EventCategory eventCategory = eventCategoryRepository.save(new EventCategory(eventCategoryDto));
        assertThat(eventCategoryRepository.findAll().size(), is(1));

        //when then
        mockMvc.perform(delete("/api/eventcategories/" + eventCategory.getId()))
                .andExpect(status().isOk());
    }
}