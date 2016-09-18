package io.codechobo.movie.domain.repository;

import io.codechobo.movie.domain.ShowingSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingSeatRepository extends JpaRepository<ShowingSeat, Long> {
}
