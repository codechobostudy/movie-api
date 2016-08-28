package io.codechobo.event.domain.repository;

import io.codechobo.event.domain.Joining;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface JoiningRepository extends JpaRepository<Joining, Long> {
}
