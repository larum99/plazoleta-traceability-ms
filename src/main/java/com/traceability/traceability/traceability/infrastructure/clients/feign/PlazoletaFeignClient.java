package com.traceability.traceability.traceability.infrastructure.clients.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "plazoleta-ms", url = "${microservices.plazoleta.url}")
public interface PlazoletaFeignClient {

    @GetMapping("/api/v1/plazoleta/orders/restaurant")
    List<Long> getOrderIdsByRestaurantId(@RequestParam("restaurantId") Long restaurantId);
}

