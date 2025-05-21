package practice.buttersaltflour.member.controller.dto;

import lombok.Getter;
import practice.buttersaltflour.member.entity.Member;

@Getter
public class CreateMemberRequest {
    private String uid;
    private String email;
    private String displayName;

    public Member toEntity() {
        return new Member(uid,email,displayName);
    }
}
