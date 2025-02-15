package dev.team08.movie_verse_backend.config;

import dev.team08.movie_verse_backend.filter.SessionTimeoutFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
    @Value("${cors.allowedOrigins:http//localhost3000}")  // ✅ Load origins from properties
    private String allowedOrigins;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)  // Add allowed IP or domain
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allows these HTTP methods
                .allowedHeaders("*")  // Allows all headers
                .allowCredentials(true);  // Allows credentials (optional, depending on your need)
    }

    @Bean
    public FilterRegistrationBean<SessionTimeoutFilter> sessionTimeoutFilter() {
        FilterRegistrationBean<SessionTimeoutFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionTimeoutFilter());
        registrationBean.addUrlPatterns("/*");  // Apply filter to all URLs
        return registrationBean;
    }
}
