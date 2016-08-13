package io.codechobo.domain.jpa;

import io.codechobo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Loustler on 8/13/16.
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
