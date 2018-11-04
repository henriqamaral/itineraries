package br.com.itinerary.usecases;

import br.com.itinerary.domains.GraphRoutes;
import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.RoutesGateway;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MountRoutPath {

  private RoutesGateway routesGateway;

  public List<RoutePath> execute(final String fromCity, final String toCity) {

    final List<Route> routes = routesGateway.findRouteByFromCityName(fromCity);

    if (routes.isEmpty()) {
      throw new RoutesNotFoundException("No Routes Found");
    }
    final List<Route> allRoutes = mountRouteList(routes, toCity);
    GraphRoutes graphRoutes = new GraphRoutes(allRoutes);
    return graphRoutes.calculate(fromCity, toCity);
  }

  private List<Route> mountRouteList(List<Route> initialRoutes, final String destinyCity) {

    List<Route> newRouteList = new ArrayList<>(initialRoutes);
    initialRoutes.forEach(
        route -> {
          if (!route.getDestiny().equals(destinyCity)) {
            final List<Route> routes = routesGateway.findRouteByFromCityName(route.getDestiny());
            newRouteList.addAll(mountRouteList(routes, destinyCity));
          }
        });

    return newRouteList;
  }
}
