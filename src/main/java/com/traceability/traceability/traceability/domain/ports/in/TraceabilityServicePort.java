package com.traceability.traceability.traceability.domain.ports.in;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;

import java.util.List;

public interface TraceabilityServicePort {
    void saveTraceabilityLog(TraceabilityModel logModel, String role, Long userId);
    List<TraceabilityModel> getTraceabilityLogsByOrderId(Long orderId, String role, Long userId);
}