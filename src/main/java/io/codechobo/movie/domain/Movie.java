package io.codechobo.movie.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Movie {

	@GeneratedValue
	@Id
	private Long no;

	/**
	 * 제목
	 */
	private String title;

	/**
	 * 장르
	 */
	//별도의 타입?
	private String genre;

	/**
	 * 러닝타임
	 */
	private Integer runningTime;

	/**
	 * 개봉일
	 */
	@Temporal(TemporalType.DATE)
	private Date releaseDate;


	@Transient // 해당 타입으로 에러 발생해서 잠시 도메인 생성시 제외처리
	/**
	 * 감독
	 */
	//TODO 별도의 타입으로?
	private List<String> diretors = new ArrayList<>();

	@Transient // 해당 타입으로 에러 발생해서 잠시 도메인 생성시 제외처리
	/**
	 * 배우
	 */
	//TODO 별도의 타입으로?
	private List<String> actors = new ArrayList<>();


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


	//TODO 영화타입 or 상영타입 or 스크린타입
	public enum MovieType {
		TWO_DIMENSION,
		THREE_DIMENSION,
	}

}
