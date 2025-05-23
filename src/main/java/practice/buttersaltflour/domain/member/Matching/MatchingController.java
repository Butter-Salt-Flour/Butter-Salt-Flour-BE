package practice.buttersaltflour.domain.member.Matching;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.buttersaltflour.domain.member.Matching.DTO.MatchingRequestDto;
import practice.buttersaltflour.domain.member.Matching.DTO.MatchingResponseDto;

@RestController
@RequiredArgsConstructor
@Tag(name = "Matching", description = "할머니-청년 매칭 관련 API")
@RequestMapping("/api/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping
    @Operation(summary = "청년이 할머니와 매칭 신청", description = "청년이 특정 할머니와 매칭을 생성합니다.")
    public ResponseEntity<MatchingResponseDto> match(@RequestBody MatchingRequestDto request) {
        return ResponseEntity.ok(matchingService.createMatching(request));
    }

    @PatchMapping("/{matchId}/approve")
    @Operation(summary = "매칭 승인", description = "할머니가 청년과의 매칭을 승인합니다.")
    public ResponseEntity<String> approveMatching(@PathVariable Long matchId) {
        matchingService.approve(matchId);
        return ResponseEntity.ok("매칭 승인 완료");
    }
}