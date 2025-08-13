package com.traceability.traceability.traceability.infrastructure.utils;

public class FeignConstants {
    private FeignConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NAME_SERVICE = "plazoleta-service";
    public static final String URL_SERVICE = "${microservices.plazoleta.url}";

    public static final String GET_ORDERS_BY_RESTAURANT = "/api/v1/plazoleta/orders/restaurant";
    public static final String EXIST_RESTAURANT_BY_ID = "/api/v1/plazoleta/exists";
    public static final String ID_PATH_VARIABLE = "restaurantId";
}
