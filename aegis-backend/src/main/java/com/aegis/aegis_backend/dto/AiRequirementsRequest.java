package com.aegis.aegis_backend.dto;


public class AiRequirementsRequest {

    private Long missionId;
    private String emergencyType;
    private String location;
    private String request;

    public AiRequirementsRequest() {}

    public AiRequirementsRequest(Long missionId, String emergencyType, String location, String request) {
        this.missionId = missionId;
        this.emergencyType = emergencyType;
        this.location = location;
        this.request = request;
    }

    public Long getMissionId() { return missionId; }
    public void setMissionId(Long missionId) { this.missionId = missionId; }

    public String getEmergencyType() { return emergencyType; }
    public void setEmergencyType(String emergencyType) { this.emergencyType = emergencyType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRequest() { return request; }
    public void setRequest(String request) { this.request = request; }
}
