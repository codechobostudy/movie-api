package io.codechobo.movie.dto;

import io.codechobo.person.dto.PersonDto;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MovieDto {

    @Id
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

    private List<PersonDto> directors = new ArrayList<>();

    private List<PersonDto> actors = new ArrayList<>();


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
        if (directors!=null) {
            this.directors.addAll(directors);
        }
    }

    public void addDirector(final PersonDto director) {
        this.directors.add(director);
    }

    public void addActor(final PersonDto actor) {
        this.actors.add(actor);
    }
}
