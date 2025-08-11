package com.traceability.traceability.traceability.application.dto.response;

import java.time.LocalDateTime;

public record TraceabilityResponse(
        String id,
        Long orderId,
        String status,
        LocalDateTime changedAt,
        Long changedBy,
        Long clientId,
        String description
) {}
