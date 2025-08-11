package com.traceability.traceability.traceability.domain.usecase;

import com.traceability.traceability.traceability.domain.exceptions.InvalidTraceabilityLogException;
import com.traceability.traceability.traceability.domain.exceptions.TraceabilityLogsNotFoundException;
import com.traceability.traceability.traceability.domain.exceptions.UnauthorizedAccessException;
import com.traceability.traceability.traceability.domain.ports.in.TraceabilityServicePort;
import com.traceability.traceability.traceability.domain.ports.out.TraceabilityPersistencePort;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;

import java.util.List;

public class TraceabilityUseCase implements TraceabilityServicePort {

    private final TraceabilityPersistencePort persistencePort;

    public TraceabilityUseCase(TraceabilityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void saveTraceabilityLog(TraceabilityModel logModel, String role, Long userId) {
        if (!"CLIENTE".equalsIgnoreCase(role) && !"EMPLEADO".equalsIgnoreCase(role)) {
            throw new UnauthorizedAccessException();
        }

        if (logModel.getOrderId() == null) {
            throw new InvalidTraceabilityLogException();
        }

        persistencePort.save(logModel);
    }

    @Override
    public List<TraceabilityModel> getTraceabilityLogsByOrderId(Long orderId, String role, Long userId) {
        if (!"CLIENTE".equalsIgnoreCase(role)) {
            throw new UnauthorizedAccessException();
        }

        List<TraceabilityModel> logs = persistencePort.findByOrderIdAndClientId(orderId, userId);

        if (logs.isEmpty()) {
            throw new TraceabilityLogsNotFoundException();
        }

        return logs;
    }

}
