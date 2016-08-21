package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	private Long no;

	@ManyToOne
	private Movie movie;

	/**
	 * 상영시작 시간
	 */
	//TODO 종료시간은 지정? 혹은 러닝타임으로 산출?
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;


	@ManyToOne
	private Screen screen;

	@OneToMany(mappedBy = "showing")
	private List<ShowingSeat> showingSeats = new ArrayList<>();


}
