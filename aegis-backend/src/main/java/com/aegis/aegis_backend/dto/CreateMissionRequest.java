package com.aegis.aegis_backend.dto;

import com.aegis.aegis_backend.entity.Mission.EmergencyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateMissionRequest {

    @NotNull(message = "emergencyType is required")
    private EmergencyType emergencyType;

    @NotBlank(message = "location is required")
    private String location;

    @NotBlank(message = "request is required")
    private String request;

    @NotNull(message = "budget is required")
    @Positive(message = "budget must be greater than 0")
    private Double budget;

    public CreateMissionRequest() {}

    public EmergencyType getEmergencyType() { return emergencyType; }
    public void setEmergencyType(EmergencyType emergencyType) { this.emergencyType = emergencyType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRequest() { return request; }
    public void setRequest(String request) { this.request = request; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
}