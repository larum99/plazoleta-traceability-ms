package com.traceability.traceability.traceability.infrastructure.adapters.security;

import com.traceability.traceability.traceability.domain.ports.in.RoleValidatorPort;
import com.traceability.traceability.traceability.infrastructure.security.utils.JwtUtil;

public class RoleValidatorAdapter implements RoleValidatorPort {
    private final JwtUtil jwtUtil;

    public RoleValidatorAdapter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String extractRole(String token) {
        return jwtUtil.extractRole(token);
    }

    @Override
    public Long extractUserId(String token) {
        return jwtUtil.extractUserId(token);
    }
}