package com.traceability.traceability.traceability.infrastructure.repositories.mongo;

import com.traceability.traceability.traceability.infrastructure.entities.TraceabilityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TraceabilityRepository extends MongoRepository<TraceabilityEntity, String> {

    List<TraceabilityEntity> findByOrderIdAndClientId(Long orderId, Long clientId);

    List<TraceabilityEntity> findByOrderIdIn(List<Long> orderIds);

}
