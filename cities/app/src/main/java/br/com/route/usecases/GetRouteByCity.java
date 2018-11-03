package br.com.route.usecases;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class GetRouteByCity {

  private final RouteGateway routeGateway;

  public List<Route> execute(final String cityName) {
    return routeGateway.getByCityName(cityName);
  }
}
