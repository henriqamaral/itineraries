package br.com.route.gateways;

import br.com.route.domain.Route;
import reactor.core.publisher.Flux;

public interface RouteGateway {

    Flux<Route> getByCityName(String cityName);
}
