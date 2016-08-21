package io.codechobo.review.domain;

import javax.persistence.*;


public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private Long reviewId;

    private String memberId;

    private int grade;

}

