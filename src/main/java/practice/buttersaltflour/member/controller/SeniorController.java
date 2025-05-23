package practice.buttersaltflour.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.member.controller.dto.SeniorResponseDto;
import practice.buttersaltflour.member.service.SeniorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Senior", description = "할머니 관련 API")
public class SeniorController {
    private final SeniorService seniorService;

    @Operation(summary = "모든 할머니 조회", description = "등록된 모든 할머니 목록을 조회합니다.")
    @GetMapping("/senior")
    public List<SeniorResponseDto> getAllSeniors() {
        return seniorService.getAllSeniors();
    }
}
