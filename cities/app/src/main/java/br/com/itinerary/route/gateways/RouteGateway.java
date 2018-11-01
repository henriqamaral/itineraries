package br.com.itinerary.route.gateways;

import br.com.itinerary.route.domain.Route;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteGateway {

    Flux<Route> getAll();

    Mono<Route> getByCityName(String cityName);
}
