package practice.buttersaltflour.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.domain.member.controller.dto.YouthRequest;
import practice.buttersaltflour.domain.member.controller.dto.YouthResponse;
import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.exception.MemberException;
import practice.buttersaltflour.domain.member.repository.YouthRepository;
import util.execption.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class YouthService {
    private final YouthRepository repository;

    public YouthResponse findYouth(String uid) {
        Youth member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return YouthResponse.from(member);
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

}
