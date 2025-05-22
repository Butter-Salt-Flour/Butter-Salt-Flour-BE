package practice.buttersaltflour.domain.gemini.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiRequest;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;
import practice.buttersaltflour.domain.gemini.service.GeminiService;

@RestController
@RequiredArgsConstructor
public class GeminiController implements GeminiApi {

    private final GeminiService geminiService;

    @Override
    public GeminiResponse createApiResponse(GeminiRequest request) {
        return geminiService.sendRequest(request.getText());
    }
}
