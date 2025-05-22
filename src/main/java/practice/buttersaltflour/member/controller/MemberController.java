package practice.buttersaltflour.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.auth.entity.CustomPrincipal;
import practice.buttersaltflour.member.controller.dto.MemberResponse;
import practice.buttersaltflour.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/member")
    public ResponseEntity<MemberResponse> findMember(@AuthenticationPrincipal CustomPrincipal customPrincipal) {
        String uid = customPrincipal.getUid();
        MemberResponse member = service.findByUid(uid);
        return ResponseEntity.ok(member);
    }
}
