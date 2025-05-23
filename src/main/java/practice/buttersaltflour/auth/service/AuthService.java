package practice.buttersaltflour.auth.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.auth.model.CustomPrincipal;
import practice.buttersaltflour.auth.exception.AuthContextMissingException;
import practice.buttersaltflour.auth.exception.InvalidTokenTypeException;
import practice.buttersaltflour.auth.exception.MissingUidException;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.exception.MemberException;
import practice.buttersaltflour.member.repository.MemberRepository;
import util.execption.ErrorCode;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final MemberRepository repository;

    public String saveIfNew() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AuthContextMissingException();
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof CustomPrincipal)) {
            throw new InvalidTokenTypeException();
        }

        CustomPrincipal customPrincipal = (CustomPrincipal) principal;
        String uid = customPrincipal.getUid();
        String email = customPrincipal.getEmail();
        String displayName = customPrincipal.getDisplayName();

        if (uid == null || uid.isBlank()) {
            log.warn("CustomPrincipal에서 UID 없음");
            throw new MissingUidException();
        }

        Optional<Member> existing = repository.findByUid(uid);
        if (existing.isEmpty()) {
            repository.save(new Member(uid, email, displayName));
            log.info("신규 회원 저장 완료: {}", uid);
        } else {
            log.info("기존 회원 로그인: {}", uid);
        }

        return "login success: " + email;
    }

    public void deleteFirebaseMember(String uid) {
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
            log.info("Firebase member 삭제 완료");
        } catch (FirebaseAuthException e) {
            throw new MemberException(ErrorCode.FIREBASE_DELETE_FAIL);
        }
    }
}
