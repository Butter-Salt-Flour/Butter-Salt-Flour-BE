package practice.buttersaltflour.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.domain.member.entity.Youth;

import java.util.Optional;

public interface YouthRepository extends JpaRepository<Youth, String> {
    Optional<Youth> findByUid(String uid);
}
