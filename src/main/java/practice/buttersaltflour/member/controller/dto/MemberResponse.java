package practice.buttersaltflour.member.controller.dto;

import lombok.Getter;
import practice.buttersaltflour.member.entity.Member;

@Getter
public class MemberResponse {
    private String email;
    private String displayName;

    public MemberResponse(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getEmail(), member.getDisplayName());
    }
}
