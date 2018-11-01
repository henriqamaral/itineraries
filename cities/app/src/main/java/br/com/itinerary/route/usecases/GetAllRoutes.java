package br.com.itinerary.route.usecases;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.RouteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class GetAllRoutes {

  private final RouteGateway routeGateway;

  public Flux<Route> execute() {
    return routeGateway.getAll();
  }
}
