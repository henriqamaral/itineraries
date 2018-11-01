package br.com.itinerary.route.gateways.mongo;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.RouteGateway;
import br.com.itinerary.route.gateways.mongo.repositories.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RouteMongoGateway implements RouteGateway {

  private final RouteRepository routeRepository;

  @Override
  public Flux<Route> getAll() {
    return routeRepository.findAll();
  }

  @Override
  public Mono<Route> getByCityName(String cityName) {
    return routeRepository.findByCity(cityName);
  }
}
