package com.traceability.traceability.traceability.domain.ports.out;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;

import java.util.List;

public interface TraceabilityPersistencePort {
    void save(TraceabilityModel logModel);
    List<TraceabilityModel> findByOrderIdAndClientId(Long orderId, Long clientId);
}
