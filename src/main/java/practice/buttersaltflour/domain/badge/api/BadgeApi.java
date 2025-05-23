package practice.buttersaltflour.domain.badge.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import practice.buttersaltflour.domain.gemini.controller.dto.GeminiResponse;

@Tag(name = "Badge API", description = "유저 뱃지 관련 API입니다.")
@RequestMapping("/api")
public interface BadgeApi {

}
