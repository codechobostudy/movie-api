package io.codechobo.infrastructure.jpa;

import io.codechobo.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-16
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
