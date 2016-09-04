package io.codechobo.event.domain.repository;

import io.codechobo.event.domain.Winning;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-21
 */
public interface WinningRepository extends JpaRepository<Winning, Long> {
}
