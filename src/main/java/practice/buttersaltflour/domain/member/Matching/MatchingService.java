package practice.buttersaltflour.domain.member.Matching;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.member.Matching.DTO.MatchingRequestDto;
import practice.buttersaltflour.domain.member.Matching.DTO.MatchingResponseDto;
import practice.buttersaltflour.domain.member.Senior.Senior;
import practice.buttersaltflour.domain.member.Senior.SeniorRepository;
import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.Youth.YouthRepository;
import practice.buttersaltflour.domain.member.enumList.MatchStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final SeniorRepository seniorRepository;
    private final YouthRepository youthRepository;

    public MatchingResponseDto createMatching(MatchingRequestDto request) {

        Senior senior = seniorRepository.findById(request.getSeniorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할머니입니다."));

        Youth youth = youthRepository.findById(request.getYouthId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 청년입니다."));

        boolean alreadyMatched = matchingRepository.existsByYouth_YouthIdAndIsMatchedIn(
                youth.getYouthId(), List.of(MatchStatus.PENDING, MatchStatus.APPROVED));
        if (alreadyMatched) {
            throw new IllegalStateException("청년은 이미 매칭된 상태입니다.");
        }

        long currentMatchingCount = matchingRepository.countBySenior_SeniorIdAndIsMatchedIn(
                senior.getSeniorId(), List.of(MatchStatus.PENDING, MatchStatus.APPROVED));
        if (currentMatchingCount >= 3) {
            throw new IllegalStateException("해당 할머니는 이미 3명과 매칭되어 있습니다.");
        }

        Matching matching = Matching.builder()
                .senior(senior)
                .youth(youth)
                .isMatched(MatchStatus.PENDING)
                .matchedAt(LocalDateTime.now())
                .build();

        matchingRepository.save(matching);

        return new MatchingResponseDto(matching.getMatchId(), "매칭 요청 완료");

    }

    public void approve (Long matchId) {
        Matching matching = matchingRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("해당 매칭이 존재하지 않습니다."));

        if (matching.getIsMatched() == MatchStatus.APPROVED) {
            throw new IllegalStateException("이미 승인된 매칭입니다.");
        }

        if (matching.getIsMatched() != MatchStatus.PENDING) {
            throw new IllegalStateException("PENDING 상태가 아닌 매칭은 승인할 수 없습니다.");
        }

        matching.setIsMatched(MatchStatus.APPROVED);
    }
    @Transactional(readOnly = true)
    public Matching findByYouth(Youth member) {
        return matchingRepository.findByYouth(member);
    }
}