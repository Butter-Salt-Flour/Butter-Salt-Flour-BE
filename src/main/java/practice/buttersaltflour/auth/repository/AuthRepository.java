package practice.buttersaltflour.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.member.entity.Member;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUid(String uid);
}