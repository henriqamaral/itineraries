package br.com.route.usecases;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateRoute {

  private final RouteGateway routeGateway;

  private final RouteValidator routeValidator;

  public Route execute(final Route route) {
    routeValidator.execute(route);
    return routeGateway.create(route);
  }

}
