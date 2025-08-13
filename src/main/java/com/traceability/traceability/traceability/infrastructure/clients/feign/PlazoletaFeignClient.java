package com.traceability.traceability.traceability.infrastructure.clients.feign;

import com.traceability.traceability.traceability.infrastructure.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = FeignConstants.NAME_SERVICE, url = FeignConstants.URL_SERVICE)
public interface PlazoletaFeignClient {

    @GetMapping(FeignConstants.GET_ORDERS_BY_RESTAURANT)
    List<Long> getOrderIdsByRestaurantId(@RequestParam(FeignConstants.ID_PATH_VARIABLE) Long restaurantId);

    @GetMapping(FeignConstants.EXIST_RESTAURANT_BY_ID)
    Boolean existsRestaurantById(@RequestParam(FeignConstants.ID_PATH_VARIABLE) Long restaurantId);
}

