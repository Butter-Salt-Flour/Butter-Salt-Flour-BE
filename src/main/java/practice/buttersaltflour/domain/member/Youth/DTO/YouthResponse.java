package practice.buttersaltflour.domain.member.Youth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.enumList.Gender;

@Getter
@Schema(description = "청년 회원 응답 DTO")
public class YouthResponse {

    @Schema(description = "청년 ID", example = "1")
    private final Long youthId;

    @Schema(description = "Firebase UID", example = "abc123uid")
    private final String uid;

    @Schema(description = "이름", example = "홍길동")
    private final String name;

    @Schema(description = "성별", example = "MALE")
    private final Gender gender;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private final String phoneNumber;

    @Schema(description = "나이", example = "20")
    private final int age;

    @Schema(description = "프로필 이미지 URL", example = "https://example.com/image.jpg")
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
