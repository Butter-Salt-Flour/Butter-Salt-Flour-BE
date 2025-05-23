package practice.buttersaltflour.domain.member.Youth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import practice.buttersaltflour.auth.model.CustomPrincipal;
import practice.buttersaltflour.domain.badge.service.BadgeService;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthRequest;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "MEMBER 정보", description = "member request API")
public class YouthController {

    private final YouthService service;
    private final BadgeService badgeService;

    @Operation(summary = "본인 정보 요청 API")
    @ApiResponse(responseCode = "200", description = "로그인한 본인 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = YouthResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/youth")
    public ResponseEntity<YouthResponse> findMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        YouthResponse member = service.findYouth(uid);
        return ResponseEntity.ok(member);
    }

    @Operation(summary = "정보 등록 API")
    @ApiResponse(responseCode = "200", description = "로그인한 본인 정보 등록 성공",
            content = @Content(schema = @Schema(implementation = YouthResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/youth")
    public ResponseEntity<Youth> saveYouth(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                           @RequestBody YouthRequest youthRequest) {
        String youthUid = customPrincipal.getUid();
        Youth youth = service.save(youthUid, youthRequest);
        return ResponseEntity.ok(youth);
    }

    @Operation(summary = "뱃지 부여 API")
    @ApiResponse(responseCode = "200", description = "뱃지 부여 성공")
    @PostMapping("/youth/{badgeId}")
    public ResponseEntity<Void> giveBadge(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                          @PathVariable Long badgeId) {
        String uid = customPrincipal.getUid();
        service.giveBadge(uid, badgeId);
        return ResponseEntity.ok().build();
    }

}
