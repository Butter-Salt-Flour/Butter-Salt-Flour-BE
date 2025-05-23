package practice.buttersaltflour.domain.member.controller.dto;

import lombok.Getter;
import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.entity.enumList.Gender;

@Getter
public class YouthRequest {

    private String name;

    private Gender gender;

    private String phoneNumber;

    private double latitude;

    private double longitude;

    private int age;

    private String profileImage;

    public Youth toEntity() {
        return Youth.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .latitude(latitude)
                .longitude(longitude)
                .age(age)
                .profileImage(profileImage)
                .build();
    }
}
