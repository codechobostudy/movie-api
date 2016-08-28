package io.codechobo.review.domain;;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Long likeCount;

    private Long hateCount;

    private String review;

    private String movieId;

    private String memberId;



}
