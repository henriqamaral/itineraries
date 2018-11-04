package br.com.route.gateways;

import br.com.route.domain.Route;
import java.util.List;

public interface RouteGateway {

  List<Route> getByCityName(String cityName);

  Route create(Route route);

  Route findRouteByAllFields(Route route);
}
