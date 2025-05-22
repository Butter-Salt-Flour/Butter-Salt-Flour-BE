package practice.buttersaltflour.member.service;

import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.exception.AuthContextMissingException;
import practice.buttersaltflour.member.exception.InvalidTokenTypeException;
import practice.buttersaltflour.member.exception.MissingUidException;
import practice.buttersaltflour.member.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository repository;

    public String saveIfNew() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
        String uid = token.getUid();

        if (authentication == null) {
            throw new AuthContextMissingException();
        }

        if (!(authentication.getPrincipal() instanceof FirebaseToken)) {
            throw new InvalidTokenTypeException();
        }


        if (uid == null || uid.isBlank()) {
            log.warn("FirebaseToken에서 UID 없음");
            throw new MissingUidException();
        }

        if (!repository.existsById(uid)) {
            repository.save(new Member(uid, token.getEmail(), token.getName()));
            log.info("신규 회원 저장 완료: {}", uid);
        } else {
            log.info("기존 회원 로그인: {}", uid);
        }

        return "login success: " + token.getEmail();
    }
}
