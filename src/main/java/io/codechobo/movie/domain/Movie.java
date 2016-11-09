package io.codechobo.movie.domain;

import io.codechobo.person.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long no;

	/**
	 * 제목
	 */
	@Column
	private String title;

	/**
	 * 장르
	 */
	@Column
	private String genre;

	/**
	 * 러닝타임
	 */
	@Column
	private Integer runningTime;

	/**
	 * 개봉일
	 */
	@Column
	@Temporal(TemporalType.DATE)
	private Date releaseDate;


	/**
	 * 감독
	 */
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	private List<MovieDirector> directors = new ArrayList<>();

	/**
	 * 배우
	 */
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	private List<MovieActor> actors = new ArrayList<>();


	public Movie(final String title, final String genre, final Integer runningTime, final Date releaseDate) {
		this(null, title, genre, runningTime, releaseDate);
	}

	public Movie(final Long no, final String title, final String genre, final Integer runningTime, final Date releaseDate) {
		this.no = no;
		this.title = title;
		this.genre = genre;
		this.runningTime = runningTime;
		this.releaseDate = releaseDate;
	}

	public void addDirector(final Person director) {
		final MovieDirector movieDirector = new MovieDirector(this, director, 1);
		this.directors.add(movieDirector);
	}

	public void addActor(final Person actor) {
		final MovieActor movieActor = new MovieActor(this, actor, 1);
		this.actors.add(movieActor);
	}



	//TODO 영화타입 or 상영타입 or 스크린타입
	public enum MovieType {
		TWO_DIMENSION,
		THREE_DIMENSION,
	}

}
