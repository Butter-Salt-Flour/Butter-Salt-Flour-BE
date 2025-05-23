package practice.buttersaltflour.domain.member.Youth.DTO;

import lombok.Builder;
import lombok.Getter;
import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.enumList.Gender;

@Getter
public class YouthResponse {
    private final Long youthId;

    private final String uid;

    private final String name;

    private final Gender gender;

    private final String phoneNumber;

    private final int age;

    private final String profileImage;

    @Builder
    public YouthResponse(Long youthId, String uid, String name, Gender gender, String phoneNumber, int age, String profileImage) {
        this.youthId = youthId;
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
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
                .age(youth.getAge())
                .profileImage(youth.getProfileImage())
                .build();
    }
}
