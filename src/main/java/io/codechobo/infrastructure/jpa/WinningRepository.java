package io.codechobo.infrastructure.jpa;

import io.codechobo.domain.event.Winning;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-21
 */
public interface WinningRepository extends JpaRepository<Winning, Long> {
}
