package br.com.itinerary.domains;

import java.util.List;
import java.util.stream.Collectors;

public class GraphRoutes {

  private final List<Route> routes;

  public GraphRoutes(final List<Route> routes) {

    this.routes = routes;
  }

  public List<RoutePath> calculate(final String startCity, final String endCity) {

    final List<Route> origins = createOriginList(startCity, routes);

    return origins
        .stream()
        .map(route -> mountPath(route, endCity, routes))
        .collect(Collectors.toList());
  }

  private RoutePath mountPath(Route route, String endCity, List<Route> searchRoutes) {

    if (route.getDestiny().equals(endCity)) {
      return new RoutePath(route, null);
    } else {
      final List<Route> origins = createOriginList(route.getDestiny(), searchRoutes);

      if (origins != null && !origins.isEmpty()) {
        final List<RoutePath> paths =
            origins
                .stream()
                .map(r -> mountPath(r, endCity, searchRoutes))
                .collect(Collectors.toList());

        RoutePath newPath = new RoutePath(route, null);
        for (RoutePath path : paths) {
          newPath = new RoutePath(newPath, path);
        }
        return newPath;
      }
    }

    return null;
  }

  private List<Route> createOriginList(final String originCity, List<Route> routes) {
    return routes
        .stream()
        .filter(route -> route.getFrom().equals(originCity))
        .collect(Collectors.toList());
  }
}
