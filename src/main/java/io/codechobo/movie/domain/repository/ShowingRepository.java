package io.codechobo.movie.domain.repository;

import io.codechobo.movie.domain.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {
}
