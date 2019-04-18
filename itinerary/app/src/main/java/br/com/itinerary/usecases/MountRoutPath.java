package br.com.itinerary.usecases;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.RoutesGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MountRoutPath {

  private RoutesGateway routesGateway;

  public Flux<RoutePath> execute(final String fromCity, final String toCity) {
    return routesGateway
        .findRouteByFromCityName(toCity)
        .flatMap(r -> findPath(r, fromCity))
        .switchIfEmpty(Mono.error(new RoutesNotFoundException("No Routes Found")));
  }

  private Mono<RoutePath> findPath(final Route route, final String originCity) {

    if (route.getFrom().equals(originCity)) {
      return Mono.just(new RoutePath(route, null));
    }

    return routesGateway
        .findRouteByFromCityName(route.getFrom())
        .flatMap(r -> findPath(r, originCity))
        .reduce(new RoutePath(route), (a, b) -> new RoutePath(b, a));
  }
}
