package br.com.route.usecases;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CreateRoute {

  private final RouteGateway routeGateway;

//  private final RouteValidator routeValidator;

  public Mono<Route> execute(final Route route) {
    return routeGateway.create(route);
  }

}
