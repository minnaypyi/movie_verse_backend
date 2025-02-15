package dev.team08.movie_verse_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {	
    @Value("${cors.allowedOrigins:http://localhost:3000}")  // ✅ Load origins from properties
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins(allowedOrigins) // Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allow HTTP methods
                        .allowedHeaders("*") // Allow all headers;
                        .allowCredentials(true); // Allow credentials (e.g., cookies, authentication)
            }
        };
    }
}
