package practice.buttersaltflour.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.auth.model.CustomPrincipal;
import practice.buttersaltflour.auth.service.AuthService;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Tag(name = "소셜로그인", description = "Auth request API")
public class AuthController {
    private final AuthService service;
    private final MemberService memberService;

    @Operation(summary = "소셜 로그인 요청")
    @ApiResponse(responseCode = "200", description = "회원가입 성공",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "LoginSuccess",
                            summary = "정상 로그인 응답",
                            value = "{\"message\": \"login success: abc@gmail.com\"}"
                    )))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/auth/signin")
    public ResponseEntity<String> protectedEndpoint() {
        String resultMessage = service.saveIfNew(); // 비즈니스 로직 위임
        return ResponseEntity.ok(resultMessage);
    }

    @DeleteMapping("/api/auth/delete")
    public ResponseEntity<String> deleteMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        service.deleteFirebaseMember(uid);
        return ResponseEntity.ok(memberService.deleteByUid(uid));
    }
}
