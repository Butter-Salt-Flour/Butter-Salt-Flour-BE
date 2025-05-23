package practice.buttersaltflour.domain.badge.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Badge API", description = "유저 뱃지 관련 API입니다.")
@RequestMapping("/api")
public interface BadgeApi {

}
