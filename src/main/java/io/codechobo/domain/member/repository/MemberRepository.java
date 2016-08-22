package io.codechobo.domain.member.repository;

import io.codechobo.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Loustler on 8/21/16.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickName(String nickname);
    Member findByEmail(String email);
}
