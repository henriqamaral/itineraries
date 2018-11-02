package br.com.itinerary.usecases;

import br.com.itinerary.domains.GraphRoutes;
import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInConnections;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.RoutesGateway;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalculateShortestWayInConnections {

  private RoutesGateway routesGateway;

  public ShortestWayInConnections execute(final String fromCity, final String toCity) {

    final List<Route> routes = routesGateway.findRouteByFromCityName(fromCity);

    if (routes.isEmpty()) {
      throw new RoutesNotFoundException("No Routes Found");
    }

    final List<Route> allRoutes = mountRouteList(routes, toCity);
    GraphRoutes graphRoutes = new GraphRoutes(allRoutes);
    final List<RoutePath> routePaths = graphRoutes.calculate(fromCity, toCity);

    return getShortestWayInConnections(routePaths);

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

  private ShortestWayInConnections getShortestWayInConnections(List<RoutePath> routePaths) {

    Comparator<RoutePath> comparator = Comparator.comparing(RoutePath::getTotalConnections);

    return routePaths
        .stream()
        .filter(Objects::nonNull).min(comparator)
        .map(ShortestWayInConnections::new)
        .orElse(null);
  }
}
