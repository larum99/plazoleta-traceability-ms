package com.traceability.traceability.traceability.infrastructure.security.utils;

public class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROLE_CLAIM = "role";
    public static final String ID_CLAIM = "id";  // usa "id" igual que plazoleta

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String[] PUBLIC_PATHS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/v3/api-docs"
    };

    // Define aquí los endpoints protegidos, por ejemplo:
    public static final String TRACEABILITY_PROTECTED_PATH = "/api/v1/traceability/**";

    public static final String APPLICATION_JSON = "application/json";

    public static final String ACCESS_DENIED_MESSAGE_TEMPLATE = """
        {
          "message": "Acceso denegado: no tienes los permisos necesarios.",
          "timestamp": "%s"
        }
    """;
}

