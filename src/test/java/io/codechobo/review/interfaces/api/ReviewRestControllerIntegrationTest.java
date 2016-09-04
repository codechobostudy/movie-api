package io.codechobo.review.interfaces.api;

import io.codechobo.review.domain.Review;
import io.codechobo.review.domain.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class ReviewRestControllerIntegrationTest {

    @Autowired
    private ReviewRepository reviewRepository;

    private Review review;

    @Before
    public void setUp() {

    }

    @Test
    public void 리뷰_정보_등록() {
        //given
        Review review = new Review(new Integer(0),new Integer(0),"영화가 재밌음", 1L,"memberId");

        //when
        Review saveReview = reviewRepository.save(review);

        //then
        assertThat(saveReview.getReviewContent(), is("영화가 재밌음"));

    }
}
