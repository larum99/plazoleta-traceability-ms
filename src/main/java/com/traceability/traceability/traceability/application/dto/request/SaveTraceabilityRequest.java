package com.traceability.traceability.traceability.application.dto.request;

import java.time.LocalDateTime;

public record SaveTraceabilityRequest(
        Long orderId,
        String status,
        LocalDateTime changedAt,
        Long changedBy,
        Long clientId,
        String description
) {}
