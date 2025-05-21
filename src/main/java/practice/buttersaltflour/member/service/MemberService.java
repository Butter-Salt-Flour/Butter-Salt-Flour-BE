package practice.buttersaltflour.member.service;

import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository repository;

    public void saveIfNew() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof FirebaseToken)) {
            throw new IllegalStateException("Firebase 인증 정보가 존재하지 않거나 형식이 올바르지 않습니다.");
        }

        FirebaseToken token = (FirebaseToken) authentication.getPrincipal();
        String uid = token.getUid();

        if (!repository.existsById(uid)) {
            repository.save(new Member(uid, token.getEmail(), token.getName()));
        }
    }
}
