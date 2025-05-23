package practice.buttersaltflour.domain.member.Matching.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MatchingResponseDto {
    private Long matchId;
    private String message;
}