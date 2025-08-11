package com.traceability.traceability.traceability.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "traceability_logs")
public class TraceabilityEntity {

    @Id
    private String id;

    private Long orderId;

    private String status;

    private LocalDateTime changedAt;

    private Long changedBy;

    private Long clientId;

    private String description;
}

