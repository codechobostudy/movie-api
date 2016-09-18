package io.codechobo.review.domain;

import javax.persistence.*;


@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY)
    private Review review;

    private String memberId;

    private int grade;

}

