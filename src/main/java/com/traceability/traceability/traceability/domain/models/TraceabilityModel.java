package com.traceability.traceability.traceability.domain.models;

import java.time.LocalDateTime;

public class TraceabilityModel {

    private String id;
    private Long orderId;
    private Long clientId;
    private String status;
    private LocalDateTime changedAt;
    private Long changedBy;
    private String description;

    public TraceabilityModel() {
    }

    public TraceabilityModel(String id, Long orderId, Long clientId, String status,
                                LocalDateTime changedAt, Long changedBy, String description) {
        this.id = id;
        this.orderId = orderId;
        this.clientId = clientId;
        this.status = status;
        this.changedAt = changedAt;
        this.changedBy = changedBy;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public Long getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Long changedBy) {
        this.changedBy = changedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
