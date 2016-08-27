package io.codechobo.theater.domain.repository;

import io.codechobo.theater.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
