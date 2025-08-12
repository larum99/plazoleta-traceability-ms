package com.traceability.traceability.traceability.domain.models;

public class EmployeeRankingModel {
    private Long employeeId;
    private double averageTimeSeconds;

    public EmployeeRankingModel() {}

    public EmployeeRankingModel(Long employeeId, double averageTimeSeconds) {
        this.employeeId = employeeId;
        this.averageTimeSeconds = averageTimeSeconds;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setAverageTimeSeconds(double averageTimeSeconds) {
        this.averageTimeSeconds = averageTimeSeconds;
    }

    public Long getEmployeeId() { return employeeId; }
    public double getAverageTimeSeconds() { return averageTimeSeconds; }
}
