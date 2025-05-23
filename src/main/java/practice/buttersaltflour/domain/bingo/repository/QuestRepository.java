package practice.buttersaltflour.domain.bingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.domain.bingo.domain.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}