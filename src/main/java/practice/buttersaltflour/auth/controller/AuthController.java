package practice.buttersaltflour.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.auth.service.AuthService;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @GetMapping("/api/login")
    public ResponseEntity<String> protectedEndpoint() {
        String resultMessage = service.saveIfNew(); // 비즈니스 로직 위임
        return ResponseEntity.ok(resultMessage);
    }
}
