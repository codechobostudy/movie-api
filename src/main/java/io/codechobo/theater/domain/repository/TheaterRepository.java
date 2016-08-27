package io.codechobo.theater.domain.repository;

import io.codechobo.theater.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
