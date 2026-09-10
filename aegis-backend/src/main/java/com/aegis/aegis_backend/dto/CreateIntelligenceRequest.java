package com.aegis.aegis_backend.dto;


import jakarta.validation.constraints.*;

public class CreateIntelligenceRequest {

    @NotBlank(message = "type is required")
    private String type;

    @NotBlank(message = "source is required")
    private String source;

    @NotBlank(message = "data is required")
    private String data;

    @NotNull(message = "confidence is required")
    @DecimalMin(value = "0.0", message = "confidence must be between 0 and 1")
    @DecimalMax(value = "1.0", message = "confidence must be between 0 and 1")
    private Double confidence;

    public CreateIntelligenceRequest() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
}
