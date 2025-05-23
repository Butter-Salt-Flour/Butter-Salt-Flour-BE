package practice.buttersaltflour.domain.member.Youth.DTO;

import lombok.Getter;

@Getter
public class UpdateMemberRequest {
    private final String email;
    private final String displayName;

    public UpdateMemberRequest(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }
}
