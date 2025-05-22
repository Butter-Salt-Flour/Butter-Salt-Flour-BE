package practice.buttersaltflour.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Butter-Salt-Flour API")
                .description("DND 해커톤 팀 버터와 소금과 밀가루 프로젝트 API 문서입니다.")
                .version("1.0.0");
    }
}
