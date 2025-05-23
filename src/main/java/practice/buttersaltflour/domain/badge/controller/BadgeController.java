package practice.buttersaltflour.domain.badge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import practice.buttersaltflour.domain.badge.api.BadgeApi;
import practice.buttersaltflour.domain.badge.service.BadgeService;

@RestController
@RequiredArgsConstructor
public class BadgeController implements BadgeApi {

    private final BadgeService badgeService;

}
