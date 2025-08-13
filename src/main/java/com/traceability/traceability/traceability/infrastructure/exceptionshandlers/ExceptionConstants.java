package com.traceability.traceability.traceability.infrastructure.exceptionshandlers;

public class ExceptionConstants {

    private ExceptionConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String UNAUTHORIZED_ACCESS_MESSAGE = "No tienes permisos para realizar esta operación.";
    public static final String INVALID_LOG_MESSAGE = "El registro de trazabilidad es inválido.";
    public static final String TRACEABILITY_LOGS_NOT_FOUND_MESSAGE = "No se encontraron registros de trazabilidad para este pedido.";
    public static final String RESTAURANT_NOT_FOUND_MESSAGE = "No se encontró el restaurante solicitado.";
}

