package practice.buttersaltflour.member.controller.dto;

import lombok.Getter;

@Getter
public class CustomPrincipal {
    private final String uid;
    private final String email;
    private final String displayName;

    public CustomPrincipal(String uid, String email, String displayName) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
    }

}
