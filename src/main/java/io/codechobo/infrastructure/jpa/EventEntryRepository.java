package io.codechobo.infrastructure.jpa;

import io.codechobo.domain.event.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface EventEntryRepository extends JpaRepository<Entry, Long> {
}
