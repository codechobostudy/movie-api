package io.codechobo.infrastructure.jpa;

import io.codechobo.domain.event.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
