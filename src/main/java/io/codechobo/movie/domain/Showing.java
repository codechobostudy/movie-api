package io.codechobo.movie.domain;

import io.codechobo.theater.domain.Screen;
import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 상영
 */
@Entity
@Getter
public class Showing {

	@GeneratedValue
	@Id
	private Long id;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private Screen screen;

	@OneToMany
	private List<ShowingSeat> showingSeats = new ArrayList<>();

	/**
	 * 상영시작 시간
	 */
	//TODO 종료시간은 지정? 혹은 러닝타임으로 산출?
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Tolerate
	public Showing() {
	}

	public Showing(Movie movie, Screen screen, Date startTime) {
		this.movie = movie;
		this.startTime = startTime;
		this.screen = screen;
	}


	public void addShowingSeat(ShowingSeat showingSeat) {
		this.showingSeats.add(showingSeat);
	}
}
