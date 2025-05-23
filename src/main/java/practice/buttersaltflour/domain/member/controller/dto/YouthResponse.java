package practice.buttersaltflour.domain.member.controller.dto;

import lombok.Builder;
import lombok.Getter;
import practice.buttersaltflour.domain.member.entity.Youth;
import practice.buttersaltflour.domain.member.entity.enumList.Gender;

@Getter
public class YouthResponse {
    private final Long youthId;

    private final String uid;

    private final String name;

    private final Gender gender;

    private final String phoneNumber;

    private final double latitude;

    private final double longitude;

    private final int age;

    private final String profileImage;

    @Builder
    public YouthResponse(Long youthId, String uid, String name, Gender gender, String phoneNumber, double latitude, double longitude, int age, String profileImage) {
        this.youthId = youthId;
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.age = age;
        this.profileImage = profileImage;
    }

    public static YouthResponse from(Youth youth) {
        return YouthResponse.builder()
                .youthId(youth.getYouthId())
                .uid(youth.getUid())
                .name(youth.getName())
                .gender(youth.getGender())
                .phoneNumber(youth.getPhoneNumber())
                .latitude(youth.getLatitude())
                .longitude(youth.getLongitude())
                .age(youth.getAge())
                .profileImage(youth.getProfileImage())
                .build();
    }
}
