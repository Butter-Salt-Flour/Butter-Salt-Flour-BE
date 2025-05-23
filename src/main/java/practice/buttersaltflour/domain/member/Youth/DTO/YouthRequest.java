package practice.buttersaltflour.domain.member.Youth.DTO;

import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.enumList.Gender;

public class YouthRequest {

    private String name;

    private Gender gender;

    private String phoneNumber;

    private String address;

    private int age;

    private String profileImage;

    public Youth toEntity() {
        return Youth.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .address(address)
                .age(age)
                .profileImage(profileImage)
                .build();
    }
}
