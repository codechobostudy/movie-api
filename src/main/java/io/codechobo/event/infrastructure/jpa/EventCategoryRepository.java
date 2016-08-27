package io.codechobo.event.infrastructure.jpa;

import io.codechobo.event.domain.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
