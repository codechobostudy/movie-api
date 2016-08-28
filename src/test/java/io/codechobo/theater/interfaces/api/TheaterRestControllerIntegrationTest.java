package io.codechobo.theater.interfaces.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.theater.domain.Theater;
import io.codechobo.theater.domain.repository.TheaterRepository;
import io.codechobo.theater.domain.support.TheaterDto;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class TheaterRestControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    private TheaterRepository theaterRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 극장_API_설정_확인_테스트() {

    }

    @Test
    public void 극장_정보를_등록한다_201_CREATED() throws Exception {

        //given
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setLocation("제주특별자치도 제주시 이도동");
        theaterDto.setName("제주시 시청점");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(theaterDto);

        //when & then
        mvc.perform(post("/api/theaters")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void 극장_리스트를_조회한다_200_OK() throws Exception {

        //given

        //when & then
        mvc.perform(get("/api/theaters"))
                .andExpect(status().isOk());
    }

    @Test
    public void 극장_상세정보를_조회한다_200_OK() throws Exception {

        //given
        Theater theater = theaterRepository.save(new Theater("제주시 시청점", "제주특별자치도 제주시 이도동"));

        //when & then
        mvc.perform(get("/api/theaters/" + theater.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void 극장_정보를_수정한다_200_OK() throws Exception {

        //given
        Theater theater = theaterRepository.save(new Theater("제주시 시청점", "제주특별자치도 제주시 이도동"));
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setLocation("제주특별자치도 제주시 이도1동");
        theaterDto.setName("제주시 시청점");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(theaterDto);


        //when & then
        String body = mvc.perform(put("/api/theaters/" + theater.getId())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(body, is("{\"id\":" + theater.getId() + ",\"name\":\"제주시 시청점\",\"location\":\"제주특별자치도 제주시 이도1동\",\"screens\":[]}"));
    }
}