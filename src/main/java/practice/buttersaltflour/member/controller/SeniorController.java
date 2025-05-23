package practice.buttersaltflour.member.controller;

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
public class SeniorController {
    private final SeniorService seniorService;

    @GetMapping("/senior")
    public List<SeniorResponseDto> getAllSeniors() {
        return seniorService.getAllSeniors();
    }



}
