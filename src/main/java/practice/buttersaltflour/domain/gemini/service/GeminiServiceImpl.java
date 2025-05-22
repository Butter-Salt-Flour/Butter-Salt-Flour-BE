package practice.buttersaltflour.domain.gemini.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;
import practice.buttersaltflour.domain.gemini.service.dto.GeminiApiResponse;

@Service
public class GeminiServiceImpl implements GeminiService {

    private final WebClient webClient;

    @Value("${gemini.service.endpoint}")
    private String endpoint;

    @Value("${gemini.service.apikey}")
    private String apikey;

    public GeminiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();
    }

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
