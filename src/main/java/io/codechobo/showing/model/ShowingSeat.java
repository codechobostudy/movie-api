package io.codechobo.showing.model;

import io.codechobo.showing.model.type.SeatState;
import lombok.Getter;

import javax.persistence.*;

import static java.util.Objects.nonNull;

@Entity
@Getter
public class ShowingSeat {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Showing showing;

    @Enumerated(EnumType.STRING)
    private SeatState state;
    private Long ticketOwnerId;

    public ShowingSeat(Showing showing) {
        this.setShowing(showing);
        this.state = SeatState.INIT;
    }

    void setShowing(Showing showing){
        if(nonNull(showing))
            showing.removeShowingSeat(this);

        showing.addShowingSeat(this);
    }
}
