package com.traceability.traceability.traceability.domain.models;

import java.time.LocalDateTime;

public class OrderEfficiencyModel {
    private Long orderId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long durationSeconds;

    public OrderEfficiencyModel() {}

    public OrderEfficiencyModel(Long orderId, LocalDateTime startTime, LocalDateTime endTime, Long durationSeconds) {
        this.orderId = orderId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationSeconds = durationSeconds;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
