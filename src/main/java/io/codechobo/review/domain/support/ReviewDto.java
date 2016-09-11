package io.codechobo.review.domain.support;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDto {

    private Long id;

    private Date regDate;

    private int likeCount;

    private int hateCount;

    private String reviewContent;

    private Long movieId;

    private String memberId;

}
