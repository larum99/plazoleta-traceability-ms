package com.traceability.traceability.traceability.domain.ports.in;

public interface RoleValidatorPort {
    String extractRole(String token);
    Long extractUserId(String token);
}
