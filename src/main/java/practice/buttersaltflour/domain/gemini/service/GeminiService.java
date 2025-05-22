package practice.buttersaltflour.domain.gemini.service;


import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;

public interface GeminiService {

    GeminiResponse sendRequest(String text);

}
