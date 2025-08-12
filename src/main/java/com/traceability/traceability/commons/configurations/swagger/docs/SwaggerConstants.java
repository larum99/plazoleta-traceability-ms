package com.traceability.traceability.commons.configurations.swagger.docs;

public class SwaggerConstants {
    private SwaggerConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String APPLICATION_JSON = "application/json";

    public static final String CREATED = "201";
    public static final String OK = "200";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";

    public static final String SUMMARY_SAVE_LOG = "Guardar un log de trazabilidad";
    public static final String DESCRIPTION_SAVE_LOG = "Permite guardar un registro de trazabilidad asociado a una orden.";

    public static final String SUMMARY_GET_LOGS = "Obtener logs de trazabilidad por orden";
    public static final String DESCRIPTION_GET_LOGS = "Devuelve la lista de logs de trazabilidad asociados a una orden específica.";

    public static final String DESCRIPTION_SAVE_LOG_SUCCESS = "Log guardado exitosamente.";
    public static final String DESCRIPTION_SAVE_LOG_BAD_REQUEST = "Datos inválidos en el request.";
    public static final String DESCRIPTION_UNAUTHORIZED = "No autorizado para realizar esta acción.";

    public static final String DESCRIPTION_GET_LOGS_SUCCESS = "Lista de logs obtenida correctamente.";
    public static final String DESCRIPTION_GET_LOGS_BAD_REQUEST = "Parámetros inválidos o faltantes.";

    public static final String EXAMPLE_NAME_SAVE_LOG_REQUEST = "Ejemplo de request para guardar log";
    public static final String EXAMPLE_NAME_TRACEABILITY_RESPONSE = "Ejemplo de respuesta de trazabilidad";

    public static final String SUMMARY_GET_ORDER_EFFICIENCIES = "Obtener eficiencia de pedidos";
    public static final String DESCRIPTION_GET_ORDER_EFFICIENCIES = "Devuelve una lista con métricas de eficiencia de pedidos para un restaurante dado.";

    public static final String SUMMARY_GET_EMPLOYEE_RANKINGS = "Obtener ranking de empleados";
    public static final String DESCRIPTION_GET_EMPLOYEE_RANKINGS = "Devuelve un ranking de empleados basado en la eficiencia para un restaurante dado.";

    public static final String DESCRIPTION_ORDER_EFFICIENCY_SUCCESS = "Lista de eficiencias de pedidos obtenida correctamente.";
    public static final String DESCRIPTION_EMPLOYEE_RANKING_SUCCESS = "Ranking de empleados obtenido correctamente.";

    public static final String DESCRIPTION_BAD_REQUEST = "Parámetros inválidos o faltantes.";

    public static final String EXAMPLE_NAME_ORDER_EFFICIENCY_RESPONSE = "Ejemplo de eficiencia de pedidos";
    public static final String EXAMPLE_NAME_EMPLOYEE_RANKING_RESPONSE = "Ejemplo de ranking de empleados";
}
