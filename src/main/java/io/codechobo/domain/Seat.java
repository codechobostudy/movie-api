package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 좌석
 */
@Entity
@Getter
public class Seat {

	@GeneratedValue
	@Id
	private Long no;

	private String seatNo;

	@ManyToOne
	private Screen screen;

}
