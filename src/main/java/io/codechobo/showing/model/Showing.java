package io.codechobo.showing.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Showing {
    @Id
    @GeneratedValue
    private Long id;
    private String movieName;
    @OneToMany(mappedBy = "showing")
    private List<ShowingSeat> seats = new ArrayList<>();

    public Showing(String movieName) {
        this.movieName = movieName;
    }

    public void addShowingSeat(ShowingSeat seat){
        this.seats.add(seat);
    }

    public void removeShowingSeat(ShowingSeat seat){
        this.seats.remove(seat);
    }
}
