package practice.buttersaltflour.domain.member.controller.dto;

import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.entity.enumList.Gender;

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
