package practice.buttersaltflour.member.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.auth.entity.CustomPrincipal;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;
import practice.buttersaltflour.member.controller.dto.MemberResponse;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Tag(name = "MEMBER 정보", description = "member request API")
public class MemberController {

    private final MemberService service;
    @Operation(summary = "본인 정보 요청 API")
    @ApiResponse(responseCode = "200", description = "Gemini 응답 성공",
            content = @Content(schema = @Schema(implementation = MemberResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        MemberResponse member = service.findByUid(uid);
        return ResponseEntity.ok(member);
    }
}
