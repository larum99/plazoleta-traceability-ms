package com.traceability.traceability.traceability.infrastructure.adapters.persistence;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.out.TraceabilityPersistencePort;
import com.traceability.traceability.traceability.infrastructure.entities.TraceabilityEntity;
import com.traceability.traceability.traceability.infrastructure.mappers.TraceabilityEntityMapper;
import com.traceability.traceability.traceability.infrastructure.repositories.mongo.TraceabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityPersistenceAdapter implements TraceabilityPersistencePort {

    private final TraceabilityRepository mongoRepository;
    private final TraceabilityEntityMapper mapper;

    @Override
    public void save(TraceabilityModel model) {
        TraceabilityEntity entity = mapper.modelToEntity(model);
        mongoRepository.save(entity);
    }

    @Override
    public List<TraceabilityModel> findByOrderIdAndClientId(Long orderId, Long clientId) {
        List<TraceabilityEntity> entities = mongoRepository.findByOrderIdAndClientId(orderId, clientId);
        return mapper.entityToModelList(entities);
    }
}

