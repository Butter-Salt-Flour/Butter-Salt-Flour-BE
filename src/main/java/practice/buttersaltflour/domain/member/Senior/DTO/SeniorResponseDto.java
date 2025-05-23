package practice.buttersaltflour.domain.member.Senior.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeniorResponseDto {
    private Long seniorId;
    private String name;
    private int age;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private String profileImage;
}
