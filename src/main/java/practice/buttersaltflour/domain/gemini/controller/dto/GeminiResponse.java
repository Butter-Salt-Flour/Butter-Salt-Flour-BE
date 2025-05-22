package practice.buttersaltflour.domain.gemini.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(
        name = "GeminiResponse",
        description = "Gemini 요청 응답"
)
@Getter
@Builder
public class GeminiResponse {

    @Schema(description = "Gemini 답변", example = "안녕하세요, Gemini입니다.")
    private String geminiResponse;

    public static final GeminiResponse toEntity(String geminiResponse) {
        return GeminiResponse.builder()
                .geminiResponse(geminiResponse)
                .build();
    }

}
