package practice.buttersaltflour.domain.gemini.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(
        name = "GeminiRequest",
        description = "Gemini에게 질문"
)
@Getter
@Builder
public class GeminiRequest {

    @Schema(description = "Gemini 질문", example = "너는 이름이 뭐니?")
    private String text;

    public static final GeminiRequest toEntity(String text) {
        return GeminiRequest.builder()
                .text(text)
                .build();
    }

}
