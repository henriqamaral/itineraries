package br.com.itinerary.route.usecases;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.RouteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GetRouteByCity {

  private final RouteGateway routeGateway;

  public Mono<Route> execute(final String cityName) {
    return routeGateway.getByCityName(cityName);
  }
}
