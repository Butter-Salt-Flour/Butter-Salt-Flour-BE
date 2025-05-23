package practice.buttersaltflour.member.controller.dto;

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