package com.aegis.aegis_backend.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AiServiceConfig {

    @Value("${ai.service.base-url}")
    private String aiServiceBaseUrl;

    @Bean
    public RestClient aiRestClient() {
        return RestClient.builder()
                .baseUrl(aiServiceBaseUrl)
                .build();
    }
}
