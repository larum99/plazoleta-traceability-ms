package com.traceability.traceability.commons.configurations.swagger.docs;

import com.traceability.traceability.commons.configurations.swagger.examples.TraceabilitySwaggerExamples;
import com.traceability.traceability.traceability.application.dto.request.SaveTraceabilityRequest;
import com.traceability.traceability.traceability.application.dto.response.TraceabilityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;
;import static com.traceability.traceability.commons.configurations.swagger.docs.SwaggerConstants.*;

public class TraceabilityControllerDocs {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = SUMMARY_SAVE_LOG,
            description = DESCRIPTION_SAVE_LOG,
            requestBody = @RequestBody(
                    description = DESCRIPTION_SAVE_LOG,
                    required = true,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = SaveTraceabilityRequest.class),
                            examples = @ExampleObject(
                                    name = EXAMPLE_NAME_SAVE_LOG_REQUEST,
                                    value = TraceabilitySwaggerExamples.SAVE_LOG_REQUEST
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = CREATED,
                    description = DESCRIPTION_SAVE_LOG_SUCCESS
            ),
            @ApiResponse(
                    responseCode = BAD_REQUEST,
                    description = DESCRIPTION_SAVE_LOG_BAD_REQUEST,
                    content = @Content(mediaType = APPLICATION_JSON)
            ),
            @ApiResponse(
                    responseCode = UNAUTHORIZED,
                    description = DESCRIPTION_UNAUTHORIZED,
                    content = @Content(mediaType = APPLICATION_JSON)
            )
    })
    public @interface SaveLogDocs {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = SUMMARY_GET_LOGS,
            description = DESCRIPTION_GET_LOGS
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = OK,
                    description = DESCRIPTION_GET_LOGS_SUCCESS,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = TraceabilityResponse.class, type = "array"),
                            examples = @ExampleObject(
                                    name = EXAMPLE_NAME_TRACEABILITY_RESPONSE,
                                    value = TraceabilitySwaggerExamples.TRACEABILITY_RESPONSE_LIST
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = BAD_REQUEST,
                    description = DESCRIPTION_GET_LOGS_BAD_REQUEST,
                    content = @Content(mediaType = APPLICATION_JSON)
            ),
            @ApiResponse(
                    responseCode = UNAUTHORIZED,
                    description = DESCRIPTION_UNAUTHORIZED,
                    content = @Content(mediaType = APPLICATION_JSON)
            )
    })
    public @interface GetLogsDocs {
    }

}
