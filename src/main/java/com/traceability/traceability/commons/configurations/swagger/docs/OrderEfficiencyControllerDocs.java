package com.traceability.traceability.commons.configurations.swagger.docs;

import com.traceability.traceability.commons.configurations.swagger.examples.OrderEfficiencySwaggerExamples;
import com.traceability.traceability.traceability.application.dto.response.EmployeeRankingResponse;
import com.traceability.traceability.traceability.application.dto.response.OrderEfficiencyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.traceability.traceability.commons.configurations.swagger.docs.SwaggerConstants.*;

public class OrderEfficiencyControllerDocs {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = SUMMARY_GET_ORDER_EFFICIENCIES,
            description = DESCRIPTION_GET_ORDER_EFFICIENCIES
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = OK,
                    description = DESCRIPTION_ORDER_EFFICIENCY_SUCCESS,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = OrderEfficiencyResponse.class, type = "array"),
                            examples = @ExampleObject(
                                    name = EXAMPLE_NAME_ORDER_EFFICIENCY_RESPONSE,
                                    value = OrderEfficiencySwaggerExamples.ORDER_EFFICIENCY_RESPONSE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = BAD_REQUEST,
                    description = DESCRIPTION_BAD_REQUEST,
                    content = @Content(mediaType = APPLICATION_JSON)
            ),
            @ApiResponse(
                    responseCode = UNAUTHORIZED,
                    description = DESCRIPTION_UNAUTHORIZED,
                    content = @Content(mediaType = APPLICATION_JSON)
            )
    })
    public @interface GetOrderEfficienciesDocs {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(
            summary = SUMMARY_GET_EMPLOYEE_RANKINGS,
            description = DESCRIPTION_GET_EMPLOYEE_RANKINGS
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = OK,
                    description = DESCRIPTION_EMPLOYEE_RANKING_SUCCESS,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = EmployeeRankingResponse.class, type = "array"),
                            examples = @ExampleObject(
                                    name = EXAMPLE_NAME_EMPLOYEE_RANKING_RESPONSE,
                                    value = OrderEfficiencySwaggerExamples.EMPLOYEE_RANKING_RESPONSE
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = BAD_REQUEST,
                    description = DESCRIPTION_BAD_REQUEST,
                    content = @Content(mediaType = APPLICATION_JSON)
            ),
            @ApiResponse(
                    responseCode = UNAUTHORIZED,
                    description = DESCRIPTION_UNAUTHORIZED,
                    content = @Content(mediaType = APPLICATION_JSON)
            )
    })
    public @interface GetEmployeeRankingsDocs {
    }
}
