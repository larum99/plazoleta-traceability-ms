package com.traceability.traceability.traceability.domain.usecase;

import com.traceability.traceability.traceability.domain.exceptions.InvalidTraceabilityLogException;
import com.traceability.traceability.traceability.domain.exceptions.TraceabilityLogsNotFoundException;
import com.traceability.traceability.traceability.domain.exceptions.UnauthorizedAccessException;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import com.traceability.traceability.traceability.domain.ports.out.TraceabilityPersistencePort;
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

import static com.traceability.traceability.traceability.domain.utils.DomainConstants.ROLE_CLIENT;
import static com.traceability.traceability.traceability.domain.utils.DomainConstants.ROLE_EMPLOYEE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceabilityUseCaseTest {

    @Mock
    private TraceabilityPersistencePort persistencePort;

    @InjectMocks
    private TraceabilityUseCase traceabilityUseCase;

    private TraceabilityModel traceabilityModel;
    private final Long ORDER_ID = 1L;
    private final Long USER_ID = 10L;

    @BeforeEach
    void setUp() {
        traceabilityModel = new TraceabilityModel(
                "id-1",
                ORDER_ID,
                USER_ID,
                "status",
                LocalDateTime.now(),
                100L,
                "description"
        );
    }

    @Test
    @DisplayName("Debe guardar un log de trazabilidad con rol de cliente")
    void saveTraceabilityLog_shouldSaveLog_whenRoleIsClient() {
        String role = ROLE_CLIENT;
        doNothing().when(persistencePort).save(traceabilityModel);

        traceabilityUseCase.saveTraceabilityLog(traceabilityModel, role, USER_ID);

        verify(persistencePort, times(1)).save(traceabilityModel);
    }

    @Test
    @DisplayName("Debe guardar un log de trazabilidad con rol de empleado")
    void saveTraceabilityLog_shouldSaveLog_whenRoleIsEmployee() {
        String role = ROLE_EMPLOYEE;
        doNothing().when(persistencePort).save(traceabilityModel);

        traceabilityUseCase.saveTraceabilityLog(traceabilityModel, role, USER_ID);

        verify(persistencePort, times(1)).save(traceabilityModel);
    }

    @Test
    @DisplayName("Debe lanzar UnauthorizedAccessException si el rol no es CLIENT ni EMPLOYEE")
    void saveTraceabilityLog_shouldThrowUnauthorizedException_whenRoleIsInvalid() {
        String invalidRole = "OWNER";

        assertThrows(UnauthorizedAccessException.class,
                () -> traceabilityUseCase.saveTraceabilityLog(traceabilityModel, invalidRole, USER_ID));
    }

    @Test
    @DisplayName("Debe lanzar InvalidTraceabilityLogException si el orderId es nulo")
    void saveTraceabilityLog_shouldThrowInvalidTraceabilityLogException_whenOrderIdIsNull() {
        String role = ROLE_CLIENT;
        traceabilityModel.setOrderId(null);

        assertThrows(InvalidTraceabilityLogException.class,
                () -> traceabilityUseCase.saveTraceabilityLog(traceabilityModel, role, USER_ID));
    }

    @Test
    @DisplayName("Debe retornar los logs de trazabilidad cuando el rol es cliente")
    void getTraceabilityLogsByOrderId_shouldReturnLogs_whenRoleIsClient() {
        String role = ROLE_CLIENT;
        List<TraceabilityModel> expectedLogs = List.of(traceabilityModel);

        when(persistencePort.findByOrderIdAndClientId(ORDER_ID, USER_ID)).thenReturn(expectedLogs);

        List<TraceabilityModel> result = traceabilityUseCase.getTraceabilityLogsByOrderId(ORDER_ID, role, USER_ID);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(expectedLogs, result);
        verify(persistencePort, times(1)).findByOrderIdAndClientId(ORDER_ID, USER_ID);
    }

    @Test
    @DisplayName("Debe lanzar UnauthorizedAccessException si el rol no es cliente")
    void getTraceabilityLogsByOrderId_shouldThrowUnauthorizedException_whenRoleIsInvalid() {
        String invalidRole = "EMPLOYEE";

        assertThrows(UnauthorizedAccessException.class,
                () -> traceabilityUseCase.getTraceabilityLogsByOrderId(ORDER_ID, invalidRole, USER_ID));
    }

    @Test
    @DisplayName("Debe lanzar TraceabilityLogsNotFoundException si no se encuentran logs")
    void getTraceabilityLogsByOrderId_shouldThrowNotFoundException_whenNoLogsFound() {
        String role = ROLE_CLIENT;
        when(persistencePort.findByOrderIdAndClientId(ORDER_ID, USER_ID)).thenReturn(Collections.emptyList());

        assertThrows(TraceabilityLogsNotFoundException.class,
                () -> traceabilityUseCase.getTraceabilityLogsByOrderId(ORDER_ID, role, USER_ID));
    }
}