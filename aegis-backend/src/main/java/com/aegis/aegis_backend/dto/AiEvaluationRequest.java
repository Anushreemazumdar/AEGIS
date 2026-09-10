package com.aegis.aegis_backend.dto;



import java.util.List;

public class AiEvaluationRequest {

    private Long missionId;
    private String emergencyType;
    private String request;
    private List<IntelligenceSnapshot> intelligence;

    public AiEvaluationRequest() {}

    public AiEvaluationRequest(Long missionId, String emergencyType, String request, List<IntelligenceSnapshot> intelligence) {
        this.missionId = missionId;
        this.emergencyType = emergencyType;
        this.request = request;
        this.intelligence = intelligence;
    }

    public Long getMissionId() { return missionId; }
    public void setMissionId(Long missionId) { this.missionId = missionId; }

    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getRequest() { return request; }
    public void setRequest(String request) { this.request = request; }

    public List<IntelligenceSnapshot> getIntelligence() { return intelligence; }
    public void setIntelligence(List<IntelligenceSnapshot> intelligence) { this.intelligence = intelligence; }
}