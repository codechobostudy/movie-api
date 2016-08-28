package io.codechobo.member.domain.repository;

import io.codechobo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Loustler on 8/21/16.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickName(String nickname);
    Member findByEmail(String email);
}
