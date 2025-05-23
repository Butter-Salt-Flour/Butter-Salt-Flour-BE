package practice.buttersaltflour.auth.model;

import lombok.Getter;

@Getter
public class CustomPrincipal {
    private final String uid;

    public CustomPrincipal(String uid) {
        this.uid = uid;
    }

}
