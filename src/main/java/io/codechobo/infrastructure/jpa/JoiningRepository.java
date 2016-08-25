package io.codechobo.infrastructure.jpa;

import io.codechobo.domain.event.Joining;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface JoiningRepository extends JpaRepository<Joining, Long> {
}
