package io.codechobo.review.interfaces.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobo.review.domain.Review;
import io.codechobo.review.domain.repository.ReviewRepository;
import io.codechobo.review.domain.support.ReviewDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class ReviewRestControllerIntegrationTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ReviewRepository reviewRepository;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void 극장_API_설정_확인_테스트(){

    }

    @Test
    public void 리뷰_정보_조회() throws Exception{
        mvc.perform(get("/api/review"))
                .andExpect(status().isOk());
    }

    @Test
    public void 리뷰_정보_등록() throws Exception{
        //given
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewContent("영화가 재미있음");
        reviewDto.setLikeCount(0);
        reviewDto.setHateCount(0);
        reviewDto.setMemberId("test");
        reviewDto.setMovieId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(reviewDto);

        //when & then
        mvc.perform(post("/api/review")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated());
    }

    @Test
    public void 리뷰_정보_수정()throws Exception{
        //given
        Review review = reviewRepository.save(new Review("영화가 재미있음",0,0,1L,"test"));
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReviewContent("영화가 재미있고 배우들의 연기도 좋았음");
        reviewDto.setMovieId(review.getMovieId());
        reviewDto.setMemberId(review.getMemberId());
        //reviewDto.setRegDate(review.getRegDate());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(reviewDto);


        //when & then
        String body = mvc.perform(put("/api/review/" + review.getId())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("body::" + body);

        assertThat(body, is("{\"id\":"+review.getId()+",\"regDate\":null,\"likeCount\":"+review.getLikeCount()+",\"hateCount\":"+review.getHateCount()+",\"reviewContent\":\"영화가 재미있고 배우들의 연기도 좋았음\",\"movieId\":"+review.getMovieId()+",\"memberId\":\""+review.getMemberId()+"\"}"));
    }
}
