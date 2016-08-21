package io.codechobo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 스크린
 */
@Entity
@Getter
public class Screen {

	@GeneratedValue
	@Id
	private Long no;


	@OneToMany(mappedBy = "screen")
	private List<Seat> seats = new ArrayList<>();


}
