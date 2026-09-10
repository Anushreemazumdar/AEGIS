package com.aegis.aegis_backend.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class X402Config {

    @Value("${x402.facilitator.base-url}")
    private String facilitatorBaseUrl;

    @Bean
    public RestClient facilitatorRestClient() {
        return RestClient.builder()
                .baseUrl(facilitatorBaseUrl)
                .build();
    }
}
