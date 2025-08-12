package com.traceability.traceability.traceability.domain.usecase;

import com.traceability.traceability.traceability.domain.exceptions.UnauthorizedAccessException;
import com.traceability.traceability.traceability.domain.models.EmployeeRankingModel;
import com.traceability.traceability.traceability.domain.models.OrderEfficiencyModel;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.out.OrderEfficiencyPersistencePort;
import com.traceability.traceability.traceability.infrastructure.clients.feign.PlazoletaFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.traceability.traceability.traceability.domain.utils.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderEfficiencyUseCaseTest {

    @Mock
    private OrderEfficiencyPersistencePort persistencePort;
    @Mock
    private PlazoletaFeignClient plazoletaFeignClient;

    @InjectMocks
    private OrderEfficiencyUseCase orderEfficiencyUseCase;

    private final Long RESTAURANT_ID = 1L;
    private final Long USER_ID = 10L;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Debe retornar la eficiencia de las órdenes para un restaurante")
    void getOrderEfficiencies_shouldReturnOrderEfficiencies_whenDataExists() {
        String role = ROLE_OWNER;
        List<Long> orderIds = List.of(1L, 2L);
        LocalDateTime time1 = LocalDateTime.now().minusMinutes(30);
        LocalDateTime time2 = LocalDateTime.now().minusMinutes(20);

        List<TraceabilityModel> logs = List.of(
                new TraceabilityModel("id1", 1L, 100L, STATUS_PENDING, time1, 10L, "USER"),
                new TraceabilityModel("id2", 1L, 100L, STATUS_DELIVERED, time2, 10L, "USER"),
                new TraceabilityModel("id3", 2L, 101L, STATUS_PENDING, time1, 10L, "USER"),
                new TraceabilityModel("id4", 2L, 101L, STATUS_DELIVERED, time2, 10L, "USER")
        );

        when(plazoletaFeignClient.getOrderIdsByRestaurantId(RESTAURANT_ID)).thenReturn(orderIds);
        when(persistencePort.findLogsByOrderIds(orderIds)).thenReturn(logs);

        List<OrderEfficiencyModel> result = orderEfficiencyUseCase.getOrderEfficiencies(RESTAURANT_ID, role, USER_ID);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getOrderId());
        assertEquals(2L, result.get(1).getOrderId());
        assertTrue(result.get(0).getDurationSeconds() > 0);
        assertTrue(result.get(1).getDurationSeconds() > 0);
    }

    @Test
    @DisplayName("Debe retornar una lista vacía cuando no hay órdenes para el restaurante")
    void getOrderEfficiencies_shouldReturnEmptyList_whenNoOrdersFound() {
        String role = ROLE_OWNER;
        when(plazoletaFeignClient.getOrderIdsByRestaurantId(RESTAURANT_ID)).thenReturn(Collections.emptyList());

        List<OrderEfficiencyModel> result = orderEfficiencyUseCase.getOrderEfficiencies(RESTAURANT_ID, role, USER_ID);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Debe lanzar UnauthorizedAccessException si el rol no es ADMIN u OWNER")
    void getOrderEfficiencies_shouldThrowUnauthorizedException_whenRoleIsInvalid() {
        String invalidRole = "EMPLOYEE";

        assertThrows(UnauthorizedAccessException.class,
                () -> orderEfficiencyUseCase.getOrderEfficiencies(RESTAURANT_ID, invalidRole, USER_ID));
    }

    @Test
    @DisplayName("Debe retornar el ranking de empleados ordenado por tiempo promedio")
    void getEmployeeRankings_shouldReturnRankings_whenDataExists() {
        String role = ROLE_ADMIN;
        List<Long> orderIds = List.of(1L, 2L);

        LocalDateTime time1_emp15 = LocalDateTime.now().minusMinutes(15);
        LocalDateTime time2_emp15 = LocalDateTime.now().minusMinutes(5);

        LocalDateTime time3_emp20 = LocalDateTime.now().minusMinutes(30);
        LocalDateTime time4_emp20 = LocalDateTime.now().minusMinutes(10);

        List<TraceabilityModel> logs = List.of(
                new TraceabilityModel("id-3", 2L, 101L, STATUS_IN_PREPARATION, time1_emp15, 15L, "in_preparation"),
                new TraceabilityModel("id-4", 2L, 101L, STATUS_DELIVERED, time2_emp15, 15L, "delivered"),

                new TraceabilityModel("id-1", 1L, 100L, STATUS_IN_PREPARATION, time3_emp20, 20L, "in_preparation"),
                new TraceabilityModel("id-2", 1L, 100L, STATUS_DELIVERED, time4_emp20, 20L, "delivered")
        );

        when(plazoletaFeignClient.getOrderIdsByRestaurantId(RESTAURANT_ID)).thenReturn(orderIds);
        when(persistencePort.findLogsByOrderIds(orderIds)).thenReturn(logs);

        List<EmployeeRankingModel> result = orderEfficiencyUseCase.getEmployeeRankings(RESTAURANT_ID, role, USER_ID);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(15L, result.get(0).getEmployeeId());
        assertEquals(20L, result.get(1).getEmployeeId());
        assertTrue(result.get(0).getAverageTimeSeconds() < result.get(1).getAverageTimeSeconds());
    }

    @Test
    @DisplayName("Debe lanzar UnauthorizedAccessException si el rol no es ADMIN u OWNER")
    void getEmployeeRankings_shouldThrowUnauthorizedException_whenRoleIsInvalid() {
        String invalidRole = "CLIENT";

        assertThrows(UnauthorizedAccessException.class,
                () -> orderEfficiencyUseCase.getEmployeeRankings(RESTAURANT_ID, invalidRole, USER_ID));
    }

    @Test
    @DisplayName("Debe retornar una lista vacía cuando no hay logs de empleados")
    void getEmployeeRankings_shouldReturnEmptyList_whenNoEmployeeLogsFound() {
        String role = ROLE_ADMIN;
        List<Long> orderIds = List.of(1L, 2L);
        List<TraceabilityModel> logs = List.of(
                new TraceabilityModel("uuid-12345", 1L, 10L, STATUS_PENDING, LocalDateTime.now(), 20L, "USER")
        );

        when(plazoletaFeignClient.getOrderIdsByRestaurantId(RESTAURANT_ID)).thenReturn(orderIds);
        when(persistencePort.findLogsByOrderIds(orderIds)).thenReturn(logs);

        List<EmployeeRankingModel> result = orderEfficiencyUseCase.getEmployeeRankings(RESTAURANT_ID, role, USER_ID);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}