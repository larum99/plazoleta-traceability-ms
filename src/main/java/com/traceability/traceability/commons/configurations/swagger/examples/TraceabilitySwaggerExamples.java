package com.traceability.traceability.commons.configurations.swagger.examples;

public class TraceabilitySwaggerExamples {
    private TraceabilitySwaggerExamples() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SAVE_LOG_REQUEST = """
        {
          "orderId": 123,
          "action": "Order created",
          "description": "Se creó el pedido con ID 123",
          "timestamp": "2025-08-12T10:30:00"
        }
    """;

    public static final String TRACEABILITY_RESPONSE_LIST = """
        [
           {
             "id": "689b4a1182108c72ec200ea9",
             "orderId": 14,
             "status": "PENDIENTE",
             "changedAt": "2025-08-12T09:05:04.931",
             "changedBy": 39,
             "clientId": 39,
             "description": "Pedido creado"
           },
           {
             "id": "689b4c0982108c72ec200eaa",
             "orderId": 14,
             "status": "EN_PREPARACION",
             "changedAt": "2025-08-12T09:13:29.25",
             "changedBy": 38,
             "clientId": 39,
             "description": "Pedido asignado y en preparación"
           }
        ]
    """;
}
