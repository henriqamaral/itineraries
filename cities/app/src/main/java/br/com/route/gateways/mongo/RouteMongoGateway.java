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
  public List<Route> getByCityName(String cityName) {
    return routeRepository.findByFrom(cityName);
  }
}
