package practice.buttersaltflour.domain.member.Youth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import practice.buttersaltflour.domain.member.Youth.Youth;
import practice.buttersaltflour.domain.member.enumList.Gender;

@Getter
@Schema(description = "청년 회원 등록 요청 DTO")
public class YouthRequest {

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "성별 (MALE 또는 FEMALE)", example = "MALE")
    private Gender gender;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "나이", example = "25")
    private int age;

    public Youth toEntity() {
        return Youth.builder()
                .name(name)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .age(age)
                .build();
    }
}
