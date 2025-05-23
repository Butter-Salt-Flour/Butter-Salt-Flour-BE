package practice.buttersaltflour.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.member.entity.Senior;

public interface SeniorRepository extends JpaRepository<Senior, Long> {
}
