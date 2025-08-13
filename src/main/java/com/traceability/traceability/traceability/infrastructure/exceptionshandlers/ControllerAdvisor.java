package com.traceability.traceability.traceability.infrastructure.exceptionshandlers;

import com.traceability.traceability.traceability.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionResponse(
                        ex.getMessage() != null ? ex.getMessage() : ExceptionConstants.UNAUTHORIZED_ACCESS_MESSAGE,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(InvalidTraceabilityLogException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidLog(InvalidTraceabilityLogException ex) {
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        ex.getMessage() != null ? ex.getMessage() : ExceptionConstants.INVALID_LOG_MESSAGE,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(TraceabilityLogsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLogsNotFound(TraceabilityLogsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(
                        ex.getMessage() != null ? ex.getMessage() : ExceptionConstants.TRACEABILITY_LOGS_NOT_FOUND_MESSAGE,
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleRestaurantNotFound(RestaurantNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(
                        ex.getMessage() != null ? ex.getMessage() : ExceptionConstants.RESTAURANT_NOT_FOUND_MESSAGE,
                        LocalDateTime.now()
                )
        );
    }
}
