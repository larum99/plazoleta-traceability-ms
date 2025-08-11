package com.traceability.traceability.traceability.infrastructure.exceptionshandlers;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime timeStamp) {
}

