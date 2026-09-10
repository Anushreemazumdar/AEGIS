package com.aegis.aegis_backend.service;


import com.aegis.aegis_backend.client.AiClient;
import com.aegis.aegis_backend.dto.*;
import com.aegis.aegis_backend.entity.DecisionEvent;
import com.aegis.aegis_backend.entity.Intelligence;
import com.aegis.aegis_backend.entity.Mission;
import com.aegis.aegis_backend.repository.DecisionEventRepository;
// NEW — Jackson 3, correct for Spring Boot 4.1.1
import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AiIntegrationService {

    private final AiClient aiClient;
    private final MissionService missionService;
    private final IntelligenceService intelligenceService;
    private final DecisionEventRepository decisionEventRepository;
    private final ObjectMapper objectMapper;

    public AiIntegrationService(AiClient aiClient,
                                MissionService missionService,
                                IntelligenceService intelligenceService,
                                DecisionEventRepository decisionEventRepository,
                                ObjectMapper objectMapper) {
        this.aiClient = aiClient;
        this.missionService = missionService;
        this.intelligenceService = intelligenceService;
        this.decisionEventRepository = decisionEventRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public AiRequirementsResponse determineRequiredIntelligence(Long missionId) {
        Mission mission = missionService.getMissionById(missionId);

        AiRequirementsRequest request = new AiRequirementsRequest(
                mission.getId(),
                mission.getEmergencyType().name(),
                mission.getLocation(),
                mission.getRequest()
        );

        AiRequirementsResponse response = aiClient.getRequiredIntelligence(request);

        logDecision(mission, "REQUIREMENTS_DETERMINED", response.getReasoning(), response);
        missionService.updateStatus(missionId, Mission.MissionStatus.GATHERING_INTELLIGENCE);

        return response;
    }

    @Transactional
    public AiEvaluationResponse evaluateGatheredIntelligence(Long missionId) {
        Mission mission = missionService.getMissionById(missionId);
        List<Intelligence> gathered = intelligenceService.getIntelligenceForMission(missionId);

        List<IntelligenceSnapshot> snapshots = gathered.stream()
                .map(intel -> new IntelligenceSnapshot(
                        intel.getType(),
                        intel.getSource(),
                        intel.getData(),
                        intel.getConfidence(),
                        intel.getTimestamp()))
                .toList();

        AiEvaluationRequest request = new AiEvaluationRequest(
                mission.getId(),
                mission.getEmergencyType().name(),
                mission.getRequest(),
                snapshots
        );

        AiEvaluationResponse response = aiClient.evaluateIntelligence(request);

        logDecision(mission, "EVALUATION_COMPLETED", response.getReasoning(), response);

        // Move the mission forward based on the AI's verdict.
        // AWAITING_PAYMENT signals Phase 4 (paid intelligence) should take over from here.
        Mission.MissionStatus nextStatus = response.isSufficient()
                ? Mission.MissionStatus.VERIFIED
                : Mission.MissionStatus.AWAITING_PAYMENT;
        missionService.updateStatus(missionId, nextStatus);

        return response;
    }

    private void logDecision(Mission mission, String eventType, String message, Object metadataObject) {
        DecisionEvent event = new DecisionEvent();
        event.setMission(mission);
        event.setEventType(eventType);
        event.setMessage(message != null ? message : "");
        event.setMetadata(toJson(metadataObject));
        decisionEventRepository.save(event);
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JacksonException e) {
            return "{\"error\":\"failed to serialize metadata\"}";
        }
    }
}