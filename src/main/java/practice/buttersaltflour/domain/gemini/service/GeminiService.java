package practice.buttersaltflour.domain.gemini.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;
import practice.buttersaltflour.domain.gemini.service.dto.GeminiApiResponse;
import practice.buttersaltflour.global.config.s3.FileData;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.service.endpoint}")
    private String endpoint;

    @Value("${gemini.service.apikey}")
    private String apikey;

    public GeminiResponse sendRequest(String text) {
        Map<String, Object> requestBody = setRequestBody(text);

        GeminiApiResponse geminiApiResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("key", apikey)
                        .build())
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(GeminiApiResponse.class)
                .block();
        return GeminiResponse.toEntity(geminiApiResponse.getCandidates().get(0)
                .getContent()
                .getParts().get(0)
                .getText());
    }

    public boolean sendRequestWithImage(FileData fileData) {
        Map<String, Object> requestBody = setRequestBody("Does this photo include old people like grandmothers and grandfathers? If it seems like it, answer OK, or answer NO.", fileData);

        GeminiApiResponse geminiApiResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("key", apikey)
                        .build())
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(GeminiApiResponse.class)
                .block();
        String check = geminiApiResponse.getCandidates().get(0)
                .getContent()
                .getParts().get(0)
                .getText();
        if (check.contains("OK")) {
            return true;
        } else {
            return false;
        }
    }

    private Map<String, Object> setRequestBody(String text, FileData fileData) {
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", text);

        Map<String, Object> inlineData = new HashMap<>();
        inlineData.put("mime_type", fileData.getMime());
        inlineData.put("data", Base64.getEncoder().encodeToString(fileData.getImageBytes()));

        Map<String, Object> imagePart = new HashMap<>();
        imagePart.put("inline_data", inlineData);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(textPart, imagePart));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", List.of(content));

        return body;
    }


    private Map<String, Object> setRequestBody(String text) {
        Map<String, Object> part = new HashMap<>();
        part.put("text", text);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", List.of(content));

        return body;
    }
}
