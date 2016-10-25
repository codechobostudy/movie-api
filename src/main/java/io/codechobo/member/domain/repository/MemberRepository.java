package io.codechobo.member.domain.repository;

import io.codechobo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author loustler
 * @since 08/21/2016
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickName(final String nickname);
    Member findByEmail(final String email);
}
