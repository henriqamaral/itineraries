package br.com.itinerary.gateways;

import br.com.itinerary.domains.Route;
import reactor.core.publisher.Flux;

public interface RoutesGateway {

  Flux<Route> findRouteByFromCityName(String fromCity);
}
