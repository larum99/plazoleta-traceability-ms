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
                "durationSeconds": 1226,
                "durationMinutes": 20.433333333333334
          },
          {
                "orderId": 14,
                "startTime": "2025-08-12T09:05:04.931",
                "endTime": "2025-08-12T09:25:31.448",
                "durationSeconds": 1226,
                "durationMinutes": 20.433333333333334
          }
        ]
    """;

    public static final String EMPLOYEE_RANKING_RESPONSE = """
        [
          {
                 "employeeId": 40,
                 "averageTimeSeconds": 590.5,
                 "averageTimeMinutes": 9.841666666666667
          },
          {
                 "employeeId": 38,
                 "averageTimeSeconds": 722,
                 "averageTimeMinutes": 12.033333333333333
          }
        ]
    """;

}
