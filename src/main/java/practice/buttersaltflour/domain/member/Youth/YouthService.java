package practice.buttersaltflour.domain.member.Youth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.badge.entity.Badge;
import practice.buttersaltflour.domain.badge.repository.BadgeRepository;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthRequest;
import practice.buttersaltflour.domain.member.Youth.DTO.YouthResponse;
import practice.buttersaltflour.domain.member.exception.MemberException;
import util.execption.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class YouthService {
    private final YouthRepository repository;
    private final BadgeRepository badgeRepository;
    private final YouthBadgeRepository youthBadgeRepository;

    public YouthResponse findYouth(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return YouthResponse.from(member);
    }

    public Youth getByUid(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return member;
    }

    public String deleteByUid(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() ->  new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        repository.delete(member);
        return "delete success: " + member.getUid();
    }

    public Youth save(String youthUid, YouthRequest youthRequest) {
        Youth youth = repository.findByUid(youthUid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        youth.resister(youthRequest);
        return youth;
    }

    public void giveBadge(String uid, Long badgeId) {
        Youth youth = repository.findByUid(uid)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 배지입니다."));

        YouthBadge youthBadge = YouthBadge.builder()
                .youth(youth)
                .badge(badge)
                .build();

        youthBadgeRepository.save(youthBadge);
    }



}
