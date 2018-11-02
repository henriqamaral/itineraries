package br.com.itinerary.gateways.client;

import br.com.itinerary.domains.Route;
import br.com.itinerary.gateways.RoutesGateway;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoutesClientGateway implements RoutesGateway {

  @Override
  public List<Route> findRouteByFromCityName(final String fromCity) {
    return Collections.emptyList();
  }
}
