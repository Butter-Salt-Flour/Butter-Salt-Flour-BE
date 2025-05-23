package practice.buttersaltflour.domain.bingo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.domain.bingo.domain.BingoBoard;
import practice.buttersaltflour.domain.member.Matching.Matching;

public interface BingoBoardRepository extends JpaRepository<BingoBoard, Long> {
    BingoBoard findByMatchingsContaining(Matching matching);}
