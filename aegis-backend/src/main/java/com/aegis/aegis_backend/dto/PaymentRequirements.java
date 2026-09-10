package com.aegis.aegis_backend.dto;


public class PaymentRequirements {

    private Long paymentId;
    private Long missionId;
    private Double amount;
    private String asset;
    private String network;
    private String payTo;
    private String description;

    public PaymentRequirements() {}

    public PaymentRequirements(Long paymentId, Long missionId, Double amount, String asset,
                               String network, String payTo, String description) {
        this.paymentId = paymentId;
        this.missionId = missionId;
        this.amount = amount;
        this.asset = asset;
        this.network = network;
        this.payTo = payTo;
        this.description = description;
    }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public Long getMissionId() { return missionId; }
    public void setMissionId(Long missionId) { this.missionId = missionId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getAsset() { return asset; }
    public void setAsset(String asset) { this.asset = asset; }

    public String getNetwork() { return network; }
    public void setNetwork(String network) { this.network = network; }

    public String getPayTo() { return payTo; }
    public void setPayTo(String payTo) { this.payTo = payTo; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}