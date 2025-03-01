package dev.team08.movie_verse_backend.config;

import dev.team08.movie_verse_backend.filter.SessionTimeoutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("http://localhost:3000")  // Add allowed IP or domain
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allows these HTTP methods
                .allowedHeaders("*") ; // Allows all headers
                //.allowCredentials(true);  // Allows credentials (optional, depending on your need)
    }

    @Bean
    public FilterRegistrationBean<SessionTimeoutFilter> sessionTimeoutFilter() {
        FilterRegistrationBean<SessionTimeoutFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionTimeoutFilter());
        registrationBean.addUrlPatterns("/*");  // Apply filter to all URLs
        return registrationBean;
    }
}
