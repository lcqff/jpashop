package SpringSecurityOauth2.domain.member.repository;

import SpringSecurityOauth2.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUuid(String uuid);
    Member findByEmail(String email);
}
