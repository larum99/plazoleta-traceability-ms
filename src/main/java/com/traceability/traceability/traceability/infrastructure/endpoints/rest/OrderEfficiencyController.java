package com.traceability.traceability.traceability.infrastructure.endpoints.rest;

import com.traceability.traceability.commons.configurations.swagger.docs.OrderEfficiencyControllerDocs.*;
import com.traceability.traceability.traceability.application.dto.response.EmployeeRankingResponse;
import com.traceability.traceability.traceability.application.dto.response.OrderEfficiencyResponse;
import com.traceability.traceability.traceability.application.services.EfficiencyService;
import com.traceability.traceability.traceability.infrastructure.utils.ControllerConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.BASE_URL)
@RequiredArgsConstructor
public class OrderEfficiencyController {

    private final EfficiencyService efficiencyService;

    @GetOrderEfficienciesDocs
    @GetMapping(ControllerConstants.ORDERS_PATH)
    @PreAuthorize(ControllerConstants.ROLE_OWNER_ADMIN)
    public ResponseEntity<List<OrderEfficiencyResponse>> getOrderEfficiencies(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam Long restaurantId) {

        String token = authorizationHeader.replace(ControllerConstants.BEARER_PREFIX, "");
        return ResponseEntity.ok(efficiencyService.getOrderEfficiencies(restaurantId, token));
    }

    @GetEmployeeRankingsDocs
    @GetMapping(ControllerConstants.EMPLOYEES_PATH)
    @PreAuthorize(ControllerConstants.ROLE_OWNER_ADMIN)
    public ResponseEntity<List<EmployeeRankingResponse>> getEmployeeRankings(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam Long restaurantId) {

        String token = authorizationHeader.replace(ControllerConstants.BEARER_PREFIX, "");
        return ResponseEntity.ok(efficiencyService.getEmployeeRankings(restaurantId, token));
    }

}
