package br.com.itinerary.domains;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GraphRoutes {

  private final Flux<Route> routes;

  public GraphRoutes(final Flux<Route> routes) {
    this.routes = routes;
  }

  public Flux<RoutePath> calculate(final String startCity, final String endCity) {

    return routes
        .filter(r -> r.getDestiny().equals(endCity))
        .flatMap(route -> findPath(route, startCity));
  }

  private Mono<RoutePath> findPath(final Route route, final String originCity) {

    if (route.getFrom().equals(originCity)) {
      return Mono.just(new RoutePath(route, null));
    }

    return routes.filter(r -> r.getDestiny().equals(route.getFrom()))
        .flatMap(r -> findPath(r, originCity))
        .reduce(new RoutePath(route), (a , b) -> new RoutePath(b, a));
  }
}
