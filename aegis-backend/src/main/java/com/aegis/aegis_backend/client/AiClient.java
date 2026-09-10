package com.aegis.aegis_backend.client;


import com.aegis.aegis_backend.dto.AiEvaluationRequest;
import com.aegis.aegis_backend.dto.AiEvaluationResponse;
import com.aegis.aegis_backend.dto.AiRequirementsRequest;
import com.aegis.aegis_backend.dto.AiRequirementsResponse;
import com.aegis.aegis_backend.exception.AiServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiClient {

    private final RestClient aiRestClient;

    public AiClient(RestClient aiRestClient) {
        this.aiRestClient = aiRestClient;
    }

    public AiRequirementsResponse getRequiredIntelligence(AiRequirementsRequest request) {
        try {
            return aiRestClient.post()
                    .uri("/analyze/requirements")
                    .body(request)
                    .retrieve()
                    .body(AiRequirementsResponse.class);
        } catch (Exception e) {
            throw new AiServiceException("Failed to get intelligence requirements from AI service", e);
        }
    }

    public AiEvaluationResponse evaluateIntelligence(AiEvaluationRequest request) {
        try {
            return aiRestClient.post()
                    .uri("/analyze/evaluate")
                    .body(request)
                    .retrieve()
                    .body(AiEvaluationResponse.class);
        } catch (Exception e) {
            throw new AiServiceException("Failed to evaluate intelligence with AI service", e);
        }
    }
}
