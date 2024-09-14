package com.ntd.recordservice;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();

        // Allow all origins, methods, and headers
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));


        // Set max age for pre-flight requests (options)
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
