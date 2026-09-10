package com.aegis.aegis_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmergencyType emergencyType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, length = 1000)
    private String request;

    @Column(nullable = false)
    private Double budget;

    @Column(nullable = false)
    private Double spent = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status = MissionStatus.CREATED;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- Enums ---

    public enum EmergencyType {
        FLOOD, FIRE, MEDICAL, EARTHQUAKE, ACCIDENT, OTHER
    }

    public enum MissionStatus {
        CREATED, GATHERING_INTELLIGENCE, AWAITING_PAYMENT, VERIFIED, COMPLETED, FAILED
    }

    // --- Constructors ---

    public Mission() {
        // Required by JPA — it needs a no-arg constructor to build objects via reflection
    }

    // --- Getters and Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EmergencyType getEmergencyType() { return emergencyType; }
    public void setEmergencyType(EmergencyType emergencyType) { this.emergencyType = emergencyType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getRequest() { return request; }
    public void setRequest(String request) { this.request = request; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public Double getSpent() { return spent; }
    public void setSpent(Double spent) { this.spent = spent; }

    public MissionStatus getStatus() { return status; }
    public void setStatus(MissionStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}