package practice.buttersaltflour.auth.model;

import lombok.Getter;

@Getter
public class CustomPrincipal {
    private final String uid;
    private final String profileImage;

    public CustomPrincipal(String uid, String profileImage) {
        this.uid = uid;
        this.profileImage = profileImage;
    }

}
