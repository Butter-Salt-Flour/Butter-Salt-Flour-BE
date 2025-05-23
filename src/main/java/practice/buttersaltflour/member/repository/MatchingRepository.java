package practice.buttersaltflour.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.member.entity.Matching;
import practice.buttersaltflour.member.entity.enumList.MatchStatus;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    boolean existsByYouthIdAndStatusIn(Long youthId, List<MatchStatus> statuses);
    long countBySeniorIdAndStatusIn(Long seniorId, List<MatchStatus> statuses);
}