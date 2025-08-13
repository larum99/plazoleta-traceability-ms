package com.traceability.traceability.traceability.application.dto.response;

import java.time.LocalDateTime;

public record OrderEfficiencyResponse(
        Long orderId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Long durationSeconds,
        Double durationMinutes
) {}
