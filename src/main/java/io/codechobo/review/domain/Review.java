package io.codechobo.review.domain;;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
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


    public Review(int likeCount, int hateCount, String reviewContent, Long movieId, String memberId){
        //this.id=id;
        this.regDate=new Date();
        this.likeCount=likeCount;
        this.hateCount=hateCount;
        this.reviewContent=reviewContent;
        this.movieId=movieId;
        this.memberId=memberId;
    }

}
