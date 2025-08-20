package com.example.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // API Info
                .info(new Info()
                        .title("Zidio Connect API 🚀")
                        .version("1.0")
                        .description("API documentation for **Zidio Connect Job Portal** with JWT Authentication.\n\n" +
                                "🔑 Use the `Authorize` button (top-right) to enter your JWT token.\n\n" +
                                "📌 Example: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...`")
                        .contact(new Contact()
                                .name("Zidio Connect Support")
                                .email("support@zidio.com")
                                .url("https://zidio.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0"))
                )

                // Servers
                .servers(List.of(
                        new Server().url("http://localhost:8889").description("Local Server"),
                        new Server().url("https://api.zidio.com").description("Production Server")
                ))

                // Security
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
