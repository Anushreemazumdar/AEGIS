package com.aegis.aegis_backend.dto;


import java.time.LocalDateTime;

public class IntelligenceSnapshot {

    private String type;
    private String source;
    private String data;
    private Double confidence;
    private LocalDateTime timestamp;

    public IntelligenceSnapshot() {}

    public IntelligenceSnapshot(String type, String source, String data, Double confidence, LocalDateTime timestamp) {
        this.type = type;
        this.source = source;
        this.data = data;
        this.confidence = confidence;
        this.timestamp = timestamp;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
