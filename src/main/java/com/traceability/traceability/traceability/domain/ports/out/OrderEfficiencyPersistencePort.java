package com.traceability.traceability.traceability.domain.ports.out;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;

import java.util.List;

public interface OrderEfficiencyPersistencePort {
    List<TraceabilityModel> findLogsByOrderIds(List<Long> orderIds);
}

