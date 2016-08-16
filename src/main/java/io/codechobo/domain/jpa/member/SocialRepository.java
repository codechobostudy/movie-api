package io.codechobo.domain.jpa.member;

import io.codechobo.domain.member.Social;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Loustler on 8/13/16.
 */
public interface SocialRepository extends JpaRepository<Social, Integer> {
}
