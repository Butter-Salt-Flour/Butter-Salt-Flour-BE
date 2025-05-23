package practice.buttersaltflour.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.buttersaltflour.member.controller.dto.MatchingRequestDto;
import practice.buttersaltflour.member.controller.dto.MatchingResponseDto;
import practice.buttersaltflour.member.service.MatchingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping
    public ResponseEntity<MatchingResponseDto> match(@RequestBody MatchingRequestDto request) {
        return ResponseEntity.ok(matchingService.createMatching(request));
    }

    @PatchMapping("/{matchId}/approve")
    public ResponseEntity<String> approveMatching(@PathVariable Long matchId) {
        matchingService.approve(matchId);
        return ResponseEntity.ok("매칭 승인 완료");
    }
}