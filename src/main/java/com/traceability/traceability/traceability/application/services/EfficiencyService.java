package com.traceability.traceability.traceability.application.services;

import com.traceability.traceability.traceability.application.dto.response.OrderEfficiencyResponse;
import com.traceability.traceability.traceability.application.dto.response.EmployeeRankingResponse;

import java.util.List;

public interface EfficiencyService {
    List<OrderEfficiencyResponse> getOrderEfficiencies(Long restaurantId, String token);
    List<EmployeeRankingResponse> getEmployeeRankings(Long restaurantId, String token);
}
