package practice.buttersaltflour.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.buttersaltflour.member.controller.dto.MemberResponse;
import practice.buttersaltflour.member.controller.dto.UpdateMemberRequest;
import practice.buttersaltflour.member.entity.Member;
import practice.buttersaltflour.member.exception.MemberException;
import practice.buttersaltflour.member.repository.MemberRepository;
import util.execption.ErrorCode;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository repository;

    public MemberResponse findByUid(String uid) {
        Member member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberResponse.from(member);
    }

    public MemberResponse updateByUid(String uid, UpdateMemberRequest request) {
        Member member = repository.findByUid(uid).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        member.update(request);
        return MemberResponse.from(member);
    }

    public String deleteByUid(String uid) {
        Member member = repository.findByUid(uid).orElseThrow(() ->  new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        repository.delete(member);
        return "delete success: " + member.getDisplayName();
    }
}
