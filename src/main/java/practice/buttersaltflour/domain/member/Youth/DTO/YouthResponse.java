package practice.buttersaltflour.domain.member.Youth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import practice.buttersaltflour.domain.member.Youth.Youth;

@Getter
public class YouthResponse {
    @Schema(description = "회원 이메일", example = "user@example.com")
    private final String email;

    @Schema(description = "회원 표시 이름", example = "홍길동")
    private final String displayName;

    public YouthResponse(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public static YouthResponse from(Youth member) {
        return new YouthResponse(member.getName(), member.getName());
    }
}
