package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class ShowingSeat {

	@GeneratedValue
	@Id
	private Long no;


	@Enumerated(EnumType.STRING)
	private ShowingSeatState state;


	@ManyToOne
	private Showing showing;

	@ManyToOne
	private Seat seat;

	public enum ShowingSeatState {

		/**
		 * 공석
		 */
		VACANCY,
		/**
		 * 점유중
		 */
		OCCUPANCY,
		/**
		 * 예약완료
		 */
		BOOKED

	}
}
