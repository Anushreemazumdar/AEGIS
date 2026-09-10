package com.aegis.aegis_backend.dto;



import java.util.List;

public class AiEvaluationResponse {

    private boolean sufficient;
    private Double overallConfidence;
    private List<String> missingTypes;
    private String reasoning;

    public AiEvaluationResponse() {}

    public boolean isSufficient() { return sufficient; }
    public void setSufficient(boolean sufficient) { this.sufficient = sufficient; }

    public Double getOverallConfidence() { return overallConfidence; }
    public void setOverallConfidence(Double overallConfidence) { this.overallConfidence = overallConfidence; }

    public List<String> getMissingTypes() { return missingTypes; }
    public void setMissingTypes(List<String> missingTypes) { this.missingTypes = missingTypes; }

    public String getReasoning() { return reasoning; }
    public void setReasoning(String reasoning) { this.reasoning = reasoning; }
}