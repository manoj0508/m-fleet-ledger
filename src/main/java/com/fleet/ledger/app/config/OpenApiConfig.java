package com.fleet.ledger.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI fleetLedgerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("M Fleet Ledger API")
                        .description("Transport and Fleet Management APIs")
                        .version("1.0.0"));
    }
}
