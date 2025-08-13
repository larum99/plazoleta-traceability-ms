package com.traceability.traceability.traceability.domain.models;

public class EmployeeRankingModel {
    private Long employeeId;
    private double averageTimeSeconds;
    private Double averageTimeMinutes;

    public EmployeeRankingModel(Long employeeId, double averageTimeSeconds) {
        this.employeeId = employeeId;
        this.averageTimeSeconds = averageTimeSeconds;
        this.averageTimeMinutes = averageTimeSeconds / 60.0;
    }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public double getAverageTimeSeconds() { return averageTimeSeconds; }
    public void setAverageTimeSeconds(double averageTimeSeconds) { this.averageTimeSeconds = averageTimeSeconds; }

    public Double getAverageTimeMinutes() { return averageTimeMinutes; }
    public void setAverageTimeMinutes(Double averageTimeMinutes) { this.averageTimeMinutes = averageTimeMinutes; }
}
