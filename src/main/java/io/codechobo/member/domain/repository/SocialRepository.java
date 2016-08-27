package io.codechobo.member.domain.repository;

import io.codechobo.member.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Loustler on 8/21/16.
 */
public interface SocialRepository extends JpaRepository<Social, Long> {
}
