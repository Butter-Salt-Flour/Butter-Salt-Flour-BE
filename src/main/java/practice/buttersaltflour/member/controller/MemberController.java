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
import org.springframework.web.bind.annotation.*;
import practice.buttersaltflour.auth.model.CustomPrincipal;
import practice.buttersaltflour.auth.service.AuthService;
import practice.buttersaltflour.member.controller.dto.MemberResponse;
import practice.buttersaltflour.member.controller.dto.UpdateMemberRequest;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "MEMBER 정보", description = "member request API")
public class MemberController {

    private final MemberService service;
    private final AuthService authService;



    @Operation(summary = "본인 정보 요청 API")
    @ApiResponse(responseCode = "200", description = "로그인한 본인 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = MemberResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        MemberResponse member = service.findByUid(uid);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/member")
    @Operation(summary = "본인 정보 수정 요청 API")
    @ApiResponse(responseCode = "200", description = "본인 정보 수정 요청 성공",
            content = @Content(schema = @Schema(implementation = MemberResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MemberResponse> updateMember(@AuthenticationPrincipal CustomPrincipal customPrincipal,
                                                       @RequestBody UpdateMemberRequest request) {
        String uid = customPrincipal.getUid();
        MemberResponse member = service.updateByUid(uid, request);
        return ResponseEntity.ok(member);
    }


}
