package com.traceability.traceability.commons.configurations.beans;

import com.traceability.traceability.traceability.domain.ports.in.OrderEfficiencyServicePort;
import com.traceability.traceability.traceability.domain.ports.in.RoleValidatorPort;
import com.traceability.traceability.traceability.domain.ports.in.TraceabilityServicePort;
import com.traceability.traceability.traceability.domain.ports.out.OrderEfficiencyPersistencePort;
import com.traceability.traceability.traceability.domain.ports.out.TraceabilityPersistencePort;
import com.traceability.traceability.traceability.domain.usecase.OrderEfficiencyUseCase;
import com.traceability.traceability.traceability.domain.usecase.TraceabilityUseCase;
import com.traceability.traceability.traceability.infrastructure.adapters.persistence.OrderEfficiencyPersistenceAdapter;
import com.traceability.traceability.traceability.infrastructure.adapters.persistence.TraceabilityPersistenceAdapter;
import com.traceability.traceability.traceability.infrastructure.adapters.security.RoleValidatorAdapter;
import com.traceability.traceability.traceability.infrastructure.clients.feign.PlazoletaFeignClient;
import com.traceability.traceability.traceability.infrastructure.mappers.TraceabilityEntityMapper;
import com.traceability.traceability.traceability.infrastructure.repositories.mongo.TraceabilityRepository;
import com.traceability.traceability.traceability.infrastructure.security.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    private final TraceabilityRepository traceabilityRepository;
    private final TraceabilityEntityMapper traceabilityEntityMapper;

    @Bean
    public TraceabilityPersistencePort traceabilityPersistencePort() {
        return new TraceabilityPersistenceAdapter(traceabilityRepository, traceabilityEntityMapper);
    }

    @Bean
    public TraceabilityServicePort traceabilityServicePort() {
        return new TraceabilityUseCase(traceabilityPersistencePort());
    }

    @Bean
    public RoleValidatorPort roleValidatorPort(JwtUtil jwtUtil) {
        return new RoleValidatorAdapter(jwtUtil);
    }

    @Bean
    public OrderEfficiencyPersistencePort orderEfficiencyPersistencePort() {
        return new OrderEfficiencyPersistenceAdapter(traceabilityRepository, traceabilityEntityMapper);
    }

    @Bean
    public OrderEfficiencyServicePort orderEfficiencyServicePort(PlazoletaFeignClient plazoletaFeignClient) {
        return new OrderEfficiencyUseCase(orderEfficiencyPersistencePort(), plazoletaFeignClient);
    }
}
