package practice.buttersaltflour.domain.member.Youth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YouthRepository extends JpaRepository<Youth, Long> {
    Optional<Youth> findByUid(String uid);
}
