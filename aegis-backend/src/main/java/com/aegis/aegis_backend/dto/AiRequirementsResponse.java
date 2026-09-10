package com.aegis.aegis_backend.dto;


import java.util.List;

public class AiRequirementsResponse {

    private List<String> requiredIntelligenceTypes;
    private String reasoning;

    public AiRequirementsResponse() {}

    public List<String> getRequiredIntelligenceTypes() { return requiredIntelligenceTypes; }
    public void setRequiredIntelligenceTypes(List<String> requiredIntelligenceTypes) {
        this.requiredIntelligenceTypes = requiredIntelligenceTypes;
    }

    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }
}
