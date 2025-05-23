package practice.buttersaltflour.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.buttersaltflour.member.controller.dto.MatchingRequestDto;
import practice.buttersaltflour.member.controller.dto.MatchingResponseDto;
import practice.buttersaltflour.member.entity.Matching;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.entity.Senior;
import practice.buttersaltflour.member.entity.enumList.MatchStatus;
import practice.buttersaltflour.member.repository.MatchingRepository;
import practice.buttersaltflour.member.repository.MemberRepository;
import practice.buttersaltflour.member.repository.SeniorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final SeniorRepository seniorRepository;
    private final MemberRepository youthRepository;

    public MatchingResponseDto createMatching(MatchingRequestDto request) {

        // 1. Senior & Youth 유효성 검증
        Senior senior = seniorRepository.findById(request.getSeniorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할머니입니다."));

        Member youth = youthRepository.findById(request.getYouthId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 청년입니다."));

        // 2. 청년은 이미 매칭되어 있는지 확인
        boolean alreadyMatched = matchingRepository.existsByYouthIdAndStatusIn(
                youth.getYouthId(), List.of(MatchStatus.PENDING, MatchStatus.APPROVED));
        if (alreadyMatched) {
            throw new IllegalStateException("청년은 이미 매칭된 상태입니다.");
        }

        // 3. 해당 할머니가 매칭 허용 범위를 초과했는지 확인
        long currentMatchingCount = matchingRepository.countBySeniorIdAndStatusIn(
                senior.getSeniorId(), List.of(MatchStatus.PENDING, MatchStatus.APPROVED));
        if (currentMatchingCount >= 3) {
            throw new IllegalStateException("해당 할머니는 이미 3명과 매칭되어 있습니다.");
        }

        // 4. 매칭 생성
        Matching matching = Matching.builder()
                .senior(senior)
                .youth(youth)
                .status(MatchStatus.PENDING)
                .matchedAt(LocalDateTime.now())
                .build();

        matchingRepository.save(matching);

        // 5. 응답 DTO 반환
        return new MatchingResponseDto(matching.getMatchId(), "매칭 요청 완료");
    }
}