package com.traceability.traceability.traceability.application.mappers;

import com.traceability.traceability.traceability.application.dto.response.OrderEfficiencyResponse;
import com.traceability.traceability.traceability.application.dto.response.EmployeeRankingResponse;
import com.traceability.traceability.traceability.domain.models.OrderEfficiencyModel;
import com.traceability.traceability.traceability.domain.models.EmployeeRankingModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EfficiencyDtoMapper {

    OrderEfficiencyResponse modelToResponse(OrderEfficiencyModel model);

    EmployeeRankingResponse modelToResponse(EmployeeRankingModel model);

    List<OrderEfficiencyResponse> orderEfficiencyModelListToResponseList(List<OrderEfficiencyModel> models);

    List<EmployeeRankingResponse> employeeRankingModelListToResponseList(List<EmployeeRankingModel> models);
}
