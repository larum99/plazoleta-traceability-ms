package com.traceability.traceability.commons.configurations.swagger.examples;

public class OrderEfficiencySwaggerExamples {
    private OrderEfficiencySwaggerExamples() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ORDER_EFFICIENCY_RESPONSE = """
        [
          {
                "orderId": 14,
                "startTime": "2025-08-12T09:05:04.931",
                "endTime": "2025-08-12T09:25:31.448",
                "durationSeconds": 1226
          },
          {
                "orderId": 14,
                "startTime": "2025-08-12T09:05:04.931",
                "endTime": "2025-08-12T09:25:31.448",
                "durationSeconds": 1226
          }
        ]
    """;

    public static final String EMPLOYEE_RANKING_RESPONSE = """
        [
          {
                 "employeeId": 38,
                 "averageTimeSeconds": 722
          },
          {
                 "employeeId": 38,
                 "averageTimeSeconds": 722
          }
        ]
    """;

}
