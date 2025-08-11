package com.traceability.traceability.traceability.application.dto.response;

import java.util.List;

public record PagedTraceabilityResponse(
        List<TraceabilityResponse> content,
        long totalElements,
        int totalPages,
        int currentPage,
        int pageSize,
        boolean isFirst,
        boolean isLast
) {}
