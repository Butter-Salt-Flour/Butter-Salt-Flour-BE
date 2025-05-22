package practice.buttersaltflour.domain.gemini.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiRequest;

@Tag(name = "Gemini 요청", description = "Gemini request API")
@RequestMapping("/gemini")
public interface GeminiApi {

    @Operation(summary = "Gemini 요청 및 응답 API")
    @ApiResponse(responseCode = "200", description = "Gemini 응답 성공",
            content = @Content(schema = @Schema(implementation = GeminiResponse.class)))
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    GeminiResponse createApiResponse(
            @Valid @RequestBody GeminiRequest request
    );

}
