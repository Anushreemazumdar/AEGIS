package com.aegis.aegis_backend.dto;

import com.aegis.aegis_backend.entity.Mission;
import java.time.LocalDateTime;

public class MissionResponse {

    private Long missionId;
    private String emergencyType;
    private String location;
    private String request;
    private Double budget;
    private Double spent;
    private String status;
    private LocalDateTime createdAt;

    public MissionResponse() {}

    // Convenience constructor: builds a response DTO directly from an entity
    public static MissionResponse fromEntity(Mission mission) {
        MissionResponse response = new MissionResponse();
        response.missionId = mission.getId();
        response.emergencyType = mission.getEmergencyType().name();
        response.location = mission.getLocation();
        response.request = mission.getRequest();
        response.budget = mission.getBudget();
        response.spent = mission.getSpent();
        response.status = mission.getStatus().name();
        response.createdAt = mission.getCreatedAt();
        return response;
    }

    public Long getMissionId() { return missionId; }
    public void setMissionId(Long missionId) { this.missionId = missionId; }

    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRequest() { return request; }
    public void setRequest(String request) { this.request = request; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public Double getSpent() { return spent; }
    public void setSpent(Double spent) { this.spent = spent; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}