package br.com.route.gateways.mongo;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RouteMongoGateway implements RouteGateway {

  private final RouteRepository routeRepository;

  @Override
  public List<Route> getByCityName(final String cityName) {
    return routeRepository.findByFrom(cityName);
  }

  @Override
  public Route create(final Route route) {
    return routeRepository.save(route);
  }

  @Override
  public Route findRouteByAllFields(final Route route) {
    return routeRepository.findByFromAndDestinyAndDepartureTimeAndArrivalTime(
        route.getFrom(), route.getDestiny(), route.getDepartureTime(), route.getArrivalTime());
  }
}
