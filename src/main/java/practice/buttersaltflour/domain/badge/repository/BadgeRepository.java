package practice.buttersaltflour.domain.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.domain.badge.entity.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
