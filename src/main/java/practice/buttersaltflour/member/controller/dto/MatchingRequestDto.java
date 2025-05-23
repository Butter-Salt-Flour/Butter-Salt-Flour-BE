package practice.buttersaltflour.member.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchingRequestDto {
    private Long seniorId;
    private Long youthId;
}

