package com.traceability.traceability.traceability.infrastructure.endpoints.rest;

import com.traceability.traceability.commons.configurations.swagger.docs.TraceabilityControllerDocs.*;
import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;
import com.traceability.traceability.traceability.application.services.TraceabilityService;
import com.traceability.traceability.traceability.infrastructure.utils.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.BASE_URL)
@RequiredArgsConstructor
public class TraceabilityController {

    private final TraceabilityService traceabilityService;

    @SaveLogDocs
    @PostMapping(ControllerConstants.LOGS_PATH)
    @PreAuthorize(ControllerConstants.ROLE_CLIENT_EMPLOYEE)
    public ResponseEntity<Void> saveLog(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestBody SaveTraceabilityRequest request) {

        String token = authorizationHeader.replace(ControllerConstants.BEARER_PREFIX, "");
        traceabilityService.saveTraceabilityLog(request, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetLogsDocs
    @GetMapping(ControllerConstants.LIST_LOGS_PATH)
    @PreAuthorize(ControllerConstants.ROLE_CLIENT)
    public ResponseEntity<List<TraceabilityResponse>> getLogs(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam Long orderId) {

        String token = authorizationHeader.replace(ControllerConstants.BEARER_PREFIX, "");
        List<TraceabilityResponse> logs = traceabilityService.getTraceabilityLogsByOrderId(orderId, token);
        return ResponseEntity.ok(logs);
    }
}
