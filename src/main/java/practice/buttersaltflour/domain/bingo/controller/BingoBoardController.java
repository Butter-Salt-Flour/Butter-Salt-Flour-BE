package practice.buttersaltflour.domain.bingo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.auth.model.CustomPrincipal;
import practice.buttersaltflour.domain.bingo.controller.response.BingoBoardResponse;
import practice.buttersaltflour.domain.bingo.service.BingoBoardService;
import practice.buttersaltflour.domain.member.entity.Matching;
import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.service.YouthService;
import practice.buttersaltflour.member.service.MatchingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Bingo", description = "Bingo API")
public class BingoBoardController {

    private final BingoBoardService bingoBoardService;
    private final YouthService youthService;
    private final MatchingService matchingService;

    @Operation(summary = "빙고판 조회 API")
    @ApiResponse(responseCode = "200", description = "빙고판 조회 성공",
            content = @Content(schema = @Schema(implementation = YouthService.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bingo")
    public ResponseEntity<BingoBoardResponse> findMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        Youth member = youthService.getByUid(uid);
        Matching matching = matchingService.findByYouth(member);
        BingoBoardResponse bingoBoard = bingoBoardService.findByMatching(matching);
        return ResponseEntity.ok(bingoBoard);
    }

}
