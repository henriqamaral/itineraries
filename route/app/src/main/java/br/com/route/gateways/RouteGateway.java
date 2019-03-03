package br.com.route.gateways;

import br.com.route.domain.Route;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteGateway {

  Flux<Route> getByCityName(String cityName);

  Mono<Route> create(Route route);
}
