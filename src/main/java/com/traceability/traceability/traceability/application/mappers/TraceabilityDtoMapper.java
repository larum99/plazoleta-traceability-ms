package com.traceability.traceability.traceability.application.mappers;

import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TraceabilityDtoMapper {

    TraceabilityModel requestToModel(SaveTraceabilityRequest dto);

    TraceabilityResponse modelToResponse(TraceabilityModel model);

    List<TraceabilityResponse> modelListToResponseList(List<TraceabilityModel> modelList);
}

