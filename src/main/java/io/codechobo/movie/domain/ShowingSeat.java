package io.codechobo.movie.domain;

import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Getter
public class ShowingSeat {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShowingSeatState state;

    @ManyToOne
    private Showing showing;

    private Long memberId;

    @Tolerate
    public ShowingSeat() {
    }

    public ShowingSeat(Showing showing) {
        this.state = ShowingSeatState.VACANCY;
        setShowing(showing);
    }

    void setShowing(Showing showing){
        this.showing = showing;
        showing.addShowingSeat(this);
    }

    public void ticket(Long memberId) {
        verifyBookingAvailable();

        this.state = ShowingSeatState.BOOKED;
        this.memberId = memberId;
    }

    public void cancel(Long memberId) {
        verifyCancellingAvailable(memberId);

        this.state = ShowingSeatState.OCCUPANCY;
        this.memberId = null;
    }


    private void verifyBookingAvailable() {
        // 이미 예약되어 있는지 확인한다.
        if (this.state != ShowingSeatState.OCCUPANCY)
            throw new IllegalArgumentException("예약이 불가능한 좌석입니다.");
    }


    private void verifyCancellingAvailable(Long memberId) {
        // 이미 예약되어 있는지 확인한다.
        if (this.state == ShowingSeatState.OCCUPANCY)
            throw new IllegalArgumentException("이미 취소된 좌석입니다.");
        else if (this.memberId != memberId)
            throw new IllegalArgumentException("예약자만 좌석을 취소할 수 있습니다.");

    }

    //=====enum======//
    //TODO State가 ShoingSeat에만 쓰인다면, State로 고쳐도 될듯.
    // 해당 enum을 사용하는 입장에서는 ShowingSeat.ShowingSeatState로 코딩해야 하므로
    // ShowingSeat를 중복해 사용하게 됨.
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
