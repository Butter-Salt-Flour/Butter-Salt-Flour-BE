package practice.buttersaltflour.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/api/protected")
    public ResponseEntity<String> protectedEndpoint() {
        String resultMessage = service.saveIfNew(); // 비즈니스 로직 위임
        return ResponseEntity.ok(resultMessage);
    }
}
