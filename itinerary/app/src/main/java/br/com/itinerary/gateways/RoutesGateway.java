package br.com.itinerary.gateways;

import br.com.itinerary.domains.Route;
import java.util.List;

public interface RoutesGateway {

  List<Route> findRouteByFromCityName(String fromCity);
}
