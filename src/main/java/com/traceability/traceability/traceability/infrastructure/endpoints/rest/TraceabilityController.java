package com.traceability.traceability.traceability.infrastructure.endpoints.rest;

import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;
import com.traceability.traceability.traceability.application.services.TraceabilityService;
import com.traceability.traceability.traceability.domain.models.TraceabilityModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/traceability")
@RequiredArgsConstructor
@Tag(name = "Traceability", description = "Endpoints for traceability logs")
public class TraceabilityController {

    private final TraceabilityService traceabilityService;

    @PostMapping("/logs")
    @PreAuthorize("hasAnyRole('CLIENTE', 'EMPLEADO')")
    public ResponseEntity<Void> saveLog(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody SaveTraceabilityRequest request) {

        String token = authorizationHeader.replace("Bearer ", "");
        traceabilityService.saveTraceabilityLog(request, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/logs")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<TraceabilityResponse>> getLogs(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam Long orderId) {

        String token = authorizationHeader.replace("Bearer ", "");
        List<TraceabilityResponse> logs = traceabilityService.getTraceabilityLogsByOrderId(orderId, token);
        return ResponseEntity.ok(logs);
    }
}
