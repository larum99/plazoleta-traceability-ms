package com.traceability.traceability.traceability.application.services;

import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;

import java.util.List;

public interface TraceabilityService {
    void saveTraceabilityLog(SaveTraceabilityRequest request, String token);
    List<TraceabilityResponse> getTraceabilityLogsByOrderId(Long orderId, String token);
}

