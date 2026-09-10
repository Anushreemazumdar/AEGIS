package com.aegis.aegis_backend.client;



import com.aegis.aegis_backend.exception.AiServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class FacilitatorClient {

    private final RestClient facilitatorRestClient;

    public FacilitatorClient(RestClient facilitatorRestClient) {
        this.facilitatorRestClient = facilitatorRestClient;
    }

    public Map<String, Object> verify(Map<String, Object> paymentPayload, Map<String, Object> paymentRequirements) {
        return call("/verify", paymentPayload, paymentRequirements);
    }

    public Map<String, Object> settle(Map<String, Object> paymentPayload, Map<String, Object> paymentRequirements) {
        return call("/settle", paymentPayload, paymentRequirements);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> call(String path, Map<String, Object> paymentPayload, Map<String, Object> paymentRequirements) {
        try {
            Map<String, Object> body = Map.of(
                    "paymentPayload", paymentPayload,
                    "paymentRequirements", paymentRequirements
            );
            return facilitatorRestClient.post()
                    .uri(path)
                    .body(body)
                    .retrieve()
                    .body(Map.class);
        } catch (Exception e) {
            throw new AiServiceException("Facilitator call to " + path + " failed", e);
        }
    }
}
