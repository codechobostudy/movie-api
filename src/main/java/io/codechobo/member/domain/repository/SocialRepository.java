package io.codechobo.member.domain.repository;

import io.codechobo.member.domain.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author loustler
 * @since 08/21/2016
 */
@Repository
public interface SocialRepository extends JpaRepository<Social, Long> {
    List<Social> findByMemberSeq(Long memberSeq);
}
