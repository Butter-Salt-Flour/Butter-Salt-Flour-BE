package practice.buttersaltflour.domain.member.Matching.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchingRequestDto {
    private Long seniorId;
    private Long youthId;
}

