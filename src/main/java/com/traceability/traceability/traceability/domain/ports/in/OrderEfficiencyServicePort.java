package com.traceability.traceability.traceability.domain.ports.in;

import com.traceability.traceability.traceability.domain.models.OrderEfficiencyModel;
import com.traceability.traceability.traceability.domain.models.EmployeeRankingModel;

import java.util.List;

public interface OrderEfficiencyServicePort {
    List<OrderEfficiencyModel> getOrderEfficiencies(Long restaurantId, String role, Long userId);
    List<EmployeeRankingModel> getEmployeeRankings(Long restaurantId, String role, Long userId);

}
