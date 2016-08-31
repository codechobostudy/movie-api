package io.codechobo.event.domain.repository;

import io.codechobo.event.domain.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
