package com.traceability.traceability.traceability.domain.usecase;

import com.traceability.traceability.traceability.domain.exceptions.UnauthorizedAccessException;
import com.traceability.traceability.traceability.domain.models.OrderEfficiencyModel;
import com.traceability.traceability.traceability.domain.models.EmployeeRankingModel;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.in.OrderEfficiencyServicePort;
import com.traceability.traceability.traceability.domain.ports.out.OrderEfficiencyPersistencePort;
import com.traceability.traceability.traceability.infrastructure.clients.feign.PlazoletaFeignClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.traceability.traceability.traceability.domain.utils.DomainConstants.*;

public class OrderEfficiencyUseCase implements OrderEfficiencyServicePort {

    private final OrderEfficiencyPersistencePort persistencePort;
    private final PlazoletaFeignClient plazoletaFeignClient;

    public OrderEfficiencyUseCase(OrderEfficiencyPersistencePort persistencePort,
                                  PlazoletaFeignClient plazoletaFeignClient) {
        this.persistencePort = persistencePort;
        this.plazoletaFeignClient = plazoletaFeignClient;
    }

    @Override
    public List<OrderEfficiencyModel> getOrderEfficiencies(Long restaurantId, String role, Long userId) {
        validateRole(role);

        List<Long> orderIds = plazoletaFeignClient.getOrderIdsByRestaurantId(restaurantId);
        if (orderIds == null || orderIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<TraceabilityModel> logs = persistencePort.findLogsByOrderIds(orderIds);
        if (logs == null || logs.isEmpty()) {
            return Collections.emptyList();
        }

        return logs.stream()
                .collect(Collectors.groupingBy(TraceabilityModel::getOrderId))
                .entrySet().stream()
                .map(entry -> {
                    Long orderId = entry.getKey();
                    List<TraceabilityModel> orderLogs = entry.getValue();

                    LocalDateTime start = getFirstChangedAt(orderLogs, STATUS_PENDING);
                    LocalDateTime end = getFirstChangedAt(orderLogs, STATUS_DELIVERED);

                    if (start != null && end != null) {
                        Long durationSeconds = Duration.between(start, end).getSeconds();
                        return new OrderEfficiencyModel(orderId, start, end, durationSeconds);
                    }

                    return null;
                })

                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeRankingModel> getEmployeeRankings(Long restaurantId, String role, Long userId) {
        validateRole(role);

        List<Long> orderIds = plazoletaFeignClient.getOrderIdsByRestaurantId(restaurantId);
        if (orderIds == null || orderIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<TraceabilityModel> logs = persistencePort.findLogsByOrderIds(orderIds);
        if (logs == null || logs.isEmpty()) {
            return Collections.emptyList();
        }

        List<TraceabilityModel> employeeLogs = logs.stream()
                .filter(log -> isEmployeeStatus(log.getStatus()))
                .collect(Collectors.toList());

        return employeeLogs.stream()
                .collect(Collectors.groupingBy(TraceabilityModel::getChangedBy))
                .entrySet().stream()
                .map(entry -> {
                    Long employeeId = entry.getKey();

                    Map<Long, List<TraceabilityModel>> logsByOrder = entry.getValue().stream()
                            .collect(Collectors.groupingBy(TraceabilityModel::getOrderId));

                    List<Long> durations = logsByOrder.values().stream()
                            .map(orderLogs -> {
                                LocalDateTime start = getFirstChangedAt(orderLogs, STATUS_IN_PREPARATION);
                                LocalDateTime end = getFirstChangedAt(orderLogs, STATUS_DELIVERED);
                                if (start != null && end != null) {
                                    return Duration.between(start, end).getSeconds();
                                }
                                return null;
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

                    double avgSeconds = durations.isEmpty()
                            ? 0.0
                            : durations.stream().mapToLong(Long::longValue).average().orElse(0.0);

                    return new EmployeeRankingModel(employeeId, avgSeconds);
                })
                .sorted(Comparator.comparingDouble(EmployeeRankingModel::getAverageTimeSeconds))
                .collect(Collectors.toList());
    }

    private void validateRole(String role) {
        if (!ROLE_OWNER.equalsIgnoreCase(role) && !ROLE_ADMIN.equalsIgnoreCase(role)) {
            throw new UnauthorizedAccessException();
        }
    }

    private boolean isEmployeeStatus(String status) {
        return EMPLOYEE_STATUSES.contains(status);
    }


    private LocalDateTime getFirstChangedAt(List<TraceabilityModel> logs, String status) {
        return logs.stream()
                .filter(log -> status.equalsIgnoreCase(log.getStatus()))
                .map(TraceabilityModel::getChangedAt)
                .min(Comparator.naturalOrder())
                .orElse(null);
    }
}
