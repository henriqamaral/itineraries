package br.com.itinerary.gateways.http.router.integration;

import br.com.itinerary.gateways.http.router.integration.jsons.RouteResource;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "router")
public interface RouterIntegration {

  @GetMapping(value = "/routes")
  List<RouteResource> findRoutes(@RequestParam("cityName") String cityName);
}
