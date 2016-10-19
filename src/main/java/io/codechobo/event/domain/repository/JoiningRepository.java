package io.codechobo.event.domain.repository;

import io.codechobo.event.domain.Joining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Kj Nam
 * @since 2016-08-17
 */
public interface JoiningRepository extends JpaRepository<Joining, Long> {
    List<Joining> findByEventId(Long eventId);

    List<Joining> findByMemberId(String memberId);
}
