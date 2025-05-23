package practice.buttersaltflour.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import practice.buttersaltflour.domain.member.entity.Matching;
import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.entity.enumList.MatchStatus;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    boolean existsByYouth_YouthIdAndIsMatchedIn(Long youthId, List<MatchStatus> statuses);
    long countBySenior_SeniorIdAndIsMatchedIn(Long seniorId, List<MatchStatus> statusList);

    Matching findByYouth(Youth member);
}