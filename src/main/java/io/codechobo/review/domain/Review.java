package io.codechobo.review.domain;;
import io.codechobo.review.domain.support.ReviewDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date regDate;

    private int likeCount;

    private int hateCount;

    private String reviewContent;

    private Long movieId;

    private String memberId;


    public Review(ReviewDto reviewDto){
        this.id=reviewDto.getId();
        this.regDate=new Date();
        this.likeCount=reviewDto.getLikeCount();
        this.hateCount=reviewDto.getHateCount();
        this.reviewContent=reviewDto.getReviewContent();
        this.movieId=reviewDto.getMovieId();
        this.memberId=reviewDto.getMemberId();
    }

    public Review(String reviewContent, int likeCount, int hateCount, Long movieId, String memberId){
        this.regDate=new Date();
        this.likeCount=likeCount;
        this.hateCount=hateCount;
        this.reviewContent=reviewContent;
        this.movieId=movieId;
        this.memberId=memberId;
    }
}
