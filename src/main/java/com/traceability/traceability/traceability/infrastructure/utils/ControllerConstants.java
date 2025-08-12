package com.traceability.traceability.traceability.infrastructure.utils;

public class ControllerConstants {

    private ControllerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = "/api/v1/traceability";
    public static final String LOGS_PATH = "/logs";
    public static final String ORDERS_PATH = "/orders";
    public static final String EMPLOYEES_PATH = "/employees";
    public static final String LIST_LOGS_PATH = "/list-logs";

    public static final String ROLE_CLIENT_EMPLOYEE = "hasAnyRole('CLIENTE', 'EMPLEADO')";
    public static final String ROLE_CLIENT = "hasRole('CLIENTE')";
    public static final String ROLE_OWNER_ADMIN = "hasAnyRole('PROPIETARIO','ADMINISTRADOR')";


    public static final String BEARER_PREFIX = "Bearer ";
}
