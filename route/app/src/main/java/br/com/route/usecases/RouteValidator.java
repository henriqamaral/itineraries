package br.com.route.usecases;

import br.com.route.domain.Route;
import br.com.route.exceptions.RouteValidationException;
import br.com.route.gateways.RouteGateway;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RouteValidator {

  private final RouteGateway routeGateway;

  public void execute(final Route route) {

    final Route routeReturned = routeGateway.findRouteByAllFields(route);
    if (Objects.nonNull(routeReturned)) {
      throw new RouteValidationException("Route already exists");
    }
  }
}
