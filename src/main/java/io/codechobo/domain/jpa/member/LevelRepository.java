package io.codechobo.domain.jpa.member;

import io.codechobo.domain.member.Level;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Loustler on 8/13/16.
 */
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
