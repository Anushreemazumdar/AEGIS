package com.aegis.aegis_backend.dto;


import com.aegis.aegis_backend.entity.Intelligence;
import java.time.LocalDateTime;

public class IntelligenceResponse {

    private Long id;
    private Long missionId;
    private String type;
    private String source;
    private String data;
    private LocalDateTime timestamp;
    private Double confidence;
    private String verificationStatus;

    public IntelligenceResponse() {}

    public static IntelligenceResponse fromEntity(Intelligence intel) {
        IntelligenceResponse response = new IntelligenceResponse();
        response.id = intel.getId();
        response.missionId = intel.getMission().getId();
        response.type = intel.getType();
        response.source = intel.getSource();
        response.data = intel.getData();
        response.timestamp = intel.getTimestamp();
        response.confidence = intel.getConfidence();
        response.verificationStatus = intel.getVerificationStatus().name();
        return response;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMissionId() { return missionId; }
    public void setMissionId(Long missionId) { this.missionId = missionId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
}