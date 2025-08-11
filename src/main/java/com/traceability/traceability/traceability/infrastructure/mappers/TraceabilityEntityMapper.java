package com.traceability.traceability.traceability.infrastructure.mappers;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.infrastructure.entities.TraceabilityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TraceabilityEntityMapper {

    TraceabilityModel entityToModel(TraceabilityEntity entity);

    TraceabilityEntity modelToEntity(TraceabilityModel model);

    List<TraceabilityModel> entityToModelList(List<TraceabilityEntity> entities);
}

