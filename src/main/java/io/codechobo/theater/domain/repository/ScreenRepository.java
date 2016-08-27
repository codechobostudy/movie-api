package io.codechobo.theater.domain.repository;

import io.codechobo.theater.domain.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
