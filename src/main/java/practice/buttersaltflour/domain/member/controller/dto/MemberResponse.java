package practice.buttersaltflour.domain.member.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import practice.buttersaltflour.domain.member.entity.Youth;

@Getter
public class MemberResponse {
    @Schema(description = "회원 이메일", example = "user@example.com")
    private final String email;

    @Schema(description = "회원 표시 이름", example = "홍길동")
    private final String displayName;

    public MemberResponse(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public static MemberResponse from(Youth member) {
        return new MemberResponse(member.getName(), member.getName());
    }
}
