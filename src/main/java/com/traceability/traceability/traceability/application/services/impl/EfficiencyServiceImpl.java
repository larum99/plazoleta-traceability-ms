package com.traceability.traceability.traceability.application.services.impl;

import com.traceability.traceability.traceability.application.dto.response.OrderEfficiencyResponse;
import com.traceability.traceability.traceability.application.dto.response.EmployeeRankingResponse;
import com.traceability.traceability.traceability.application.mappers.EfficiencyDtoMapper;
import com.traceability.traceability.traceability.application.services.EfficiencyService;
import com.traceability.traceability.traceability.domain.models.OrderEfficiencyModel;
import com.traceability.traceability.traceability.domain.models.EmployeeRankingModel;
import com.traceability.traceability.traceability.domain.ports.in.OrderEfficiencyServicePort;
import com.traceability.traceability.traceability.domain.ports.in.RoleValidatorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EfficiencyServiceImpl implements EfficiencyService {

    private final OrderEfficiencyServicePort efficiencyServicePort;
    private final EfficiencyDtoMapper efficiencyDtoMapper;
    private final RoleValidatorPort roleValidatorPort;

    @Override
    public List<OrderEfficiencyResponse> getOrderEfficiencies(Long restaurantId, String token) {
        String role = roleValidatorPort.extractRole(token);
        Long userId = roleValidatorPort.extractUserId(token);

        List<OrderEfficiencyModel> models = efficiencyServicePort.getOrderEfficiencies(restaurantId, role, userId);

        return efficiencyDtoMapper.orderEfficiencyModelListToResponseList(models);
    }

    @Override
    public List<EmployeeRankingResponse> getEmployeeRankings(Long restaurantId, String token) {
        String role = roleValidatorPort.extractRole(token);
        Long userId = roleValidatorPort.extractUserId(token);

        List<EmployeeRankingModel> models = efficiencyServicePort.getEmployeeRankings(restaurantId, role, userId);

        return efficiencyDtoMapper.employeeRankingModelListToResponseList(models);
    }

}
