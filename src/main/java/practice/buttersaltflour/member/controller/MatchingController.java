package practice.buttersaltflour.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.member.controller.dto.MatchingRequestDto;
import practice.buttersaltflour.member.controller.dto.MatchingResponseDto;
import practice.buttersaltflour.member.service.MatchingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping
    public ResponseEntity<MatchingResponseDto> match(@RequestBody MatchingRequestDto request) {
        return ResponseEntity.ok(matchingService.createMatching(request));
    }
}