package io.codechobo.member.domain.repository;

import io.codechobo.member.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author loustler
 * @since 08/21/2016
 */
public interface SocialRepository extends JpaRepository<Social, Long> {
    List<Social> findByMemberSequence(Long memberSequence);
}
