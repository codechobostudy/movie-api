package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
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


	/**
	 * 감독
	 */
	//TODO 별도의 타입으로?
	private List<String> diretors = new ArrayList<>();

	/**
	 * 배우
	 */
	//TODO 별도의 타입으로?
	private List<String> actors = new ArrayList<>();

	//TODO 영화타입 or 상영타입 or 스크린타입
	public enum MovieType {
		TWO_DIMENSION,
		THREE_DIMENSION,
	}

}
