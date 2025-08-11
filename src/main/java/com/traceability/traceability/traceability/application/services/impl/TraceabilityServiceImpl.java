package com.traceability.traceability.traceability.application.services.impl;

import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;
import com.traceability.traceability.traceability.application.mappers.TraceabilityDtoMapper;
import com.traceability.traceability.traceability.application.services.TraceabilityService;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.in.TraceabilityServicePort;
import com.traceability.traceability.traceability.domain.ports.in.RoleValidatorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityServiceImpl implements TraceabilityService {

    private final TraceabilityServicePort traceabilityServicePort;
    private final TraceabilityDtoMapper traceabilityDtoMapper;
    private final RoleValidatorPort roleValidatorPort;

    @Override
    public void saveTraceabilityLog(SaveTraceabilityRequest request, String token) {
        String role = roleValidatorPort.extractRole(token);
        Long userId = roleValidatorPort.extractUserId(token);

        TraceabilityModel model = traceabilityDtoMapper.requestToModel(request);

        traceabilityServicePort.saveTraceabilityLog(model, role, userId);
    }

    @Override
    public List<TraceabilityResponse> getTraceabilityLogsByOrderId(Long orderId, String token) {
        String role = roleValidatorPort.extractRole(token);
        Long userId = roleValidatorPort.extractUserId(token);

        List<TraceabilityModel> models = traceabilityServicePort.getTraceabilityLogsByOrderId(orderId, role, userId);

        return traceabilityDtoMapper.modelListToResponseList(models);
    }
}
