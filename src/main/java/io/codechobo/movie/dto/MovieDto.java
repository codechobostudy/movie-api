package io.codechobo.movie.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class MovieDto {

    private Long no;

    /**
     * 제목
     */
    private String title;

    /**
     * 장르
     */
    private String genre;

    /**
     * 러닝타임
     */
    private Integer runningTime;

    /**
     * 개봉일
     */
    private Date releaseDate;

    public MovieDto(final Long no) {
        this.no = no;
    }

    public MovieDto(final String title, final String genre, final Integer runningTime, final Date releaseDate) {
        this(null, title, genre, runningTime, releaseDate);
    }

    public MovieDto(final Long no, final String title, final String genre, final Integer runningTime, final Date releaseDate) {
        this.no = no;
        this.title = title;
        this.genre = genre;
        this.runningTime = runningTime;
        if (releaseDate!=null) {
            this.releaseDate = new Date(releaseDate.getTime());
        }
    }
}
