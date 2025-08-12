package com.traceability.traceability.traceability.infrastructure.adapters.persistence;

import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.out.OrderEfficiencyPersistencePort;
import com.traceability.traceability.traceability.infrastructure.mappers.TraceabilityEntityMapper;
import com.traceability.traceability.traceability.infrastructure.repositories.mongo.TraceabilityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEfficiencyPersistenceAdapter implements OrderEfficiencyPersistencePort {

    private final TraceabilityRepository traceabilityRepository;
    private final TraceabilityEntityMapper traceabilityEntityMapper;


    public OrderEfficiencyPersistenceAdapter(TraceabilityRepository traceabilityRepository,
                                             TraceabilityEntityMapper traceabilityEntityMapper) {
        this.traceabilityRepository = traceabilityRepository;
        this.traceabilityEntityMapper = traceabilityEntityMapper;
    }

    @Override
    public List<TraceabilityModel> findLogsByOrderIds(List<Long> orderIds) {
        return traceabilityEntityMapper.entityToModelList(
                traceabilityRepository.findByOrderIdIn(orderIds)
        );
    }
}
