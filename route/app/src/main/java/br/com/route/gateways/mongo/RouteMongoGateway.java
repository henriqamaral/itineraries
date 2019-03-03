package br.com.route.gateways.mongo;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RouteMongoGateway implements RouteGateway {

  private final RouteRepository routeRepository;

  @Override
  public Flux<Route> getByCityName(final String cityName) {
    return routeRepository.findByFrom(cityName);
  }

  @Override
  public Mono<Route> create(final Route route) {
    return routeRepository.save(route);
  }
}
