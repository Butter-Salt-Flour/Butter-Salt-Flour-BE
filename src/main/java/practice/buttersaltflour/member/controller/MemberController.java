package practice.buttersaltflour.member.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/api/protected")
    public ResponseEntity<String> protectedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof FirebaseToken)) {
            return ResponseEntity.status(401).body("인증 실패 또는 FirebaseToken 없음");
        }

        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
        service.saveIfNew();
        return ResponseEntity.ok("login success: " + token.getEmail());
    }
}

