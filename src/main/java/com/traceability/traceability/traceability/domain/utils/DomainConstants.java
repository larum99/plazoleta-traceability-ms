package com.traceability.traceability.traceability.domain.utils;

import java.util.List;

public final class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROLE_OWNER = "PROPIETARIO";
    public static final String ROLE_ADMIN = "ADMINISTRADOR";
    public static final String ROLE_CLIENT = "CLIENTE";
    public static final String ROLE_EMPLOYEE = "EMPLEADO";

    public static final String STATUS_PENDING = "PENDIENTE";
    public static final String STATUS_IN_PREPARATION = "EN_PREPARACION";
    public static final String STATUS_READY = "LISTO";
    public static final String STATUS_DELIVERED = "ENTREGADO";

    public static final List<String> EMPLOYEE_STATUSES = List.of(
            STATUS_IN_PREPARATION,
            STATUS_READY,
            STATUS_DELIVERED
    );
}

