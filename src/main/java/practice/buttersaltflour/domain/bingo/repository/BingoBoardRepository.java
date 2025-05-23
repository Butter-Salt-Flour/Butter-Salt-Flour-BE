package practice.buttersaltflour.domain.bingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.buttersaltflour.domain.bingo.domain.BingoBoard;
import practice.buttersaltflour.domain.member.entity.Matching;

@Repository
public interface BingoBoardRepository extends JpaRepository<BingoBoard, Long> {
    BingoBoard findByMatching(Matching matching);
}
