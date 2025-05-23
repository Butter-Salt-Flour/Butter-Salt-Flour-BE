package practice.buttersaltflour.auth.model;

import lombok.Getter;

@Getter
public class CustomPrincipal {
    private final String uid;
    private final String picture;

    public CustomPrincipal(String uid, String picture) {
        this.uid = uid;
        this.picture = picture;
    }

}
