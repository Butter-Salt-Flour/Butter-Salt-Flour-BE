package practice.buttersaltflour.domain.member.Youth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthResponse;
import practice.buttersaltflour.domain.member.exception.MemberException;
import util.execption.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class YouthService {
    private final YouthRepository repository;

    public YouthResponse findByUid(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return YouthResponse.from(member);
    }

    public String deleteByUid(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() ->  new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        repository.delete(member);
        return "delete success: " + member.getUid();
    }
}
