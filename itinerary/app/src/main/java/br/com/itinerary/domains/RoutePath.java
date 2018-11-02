package br.com.itinerary.domains;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoutePath {

  private static final int MIN_CONNECTION = 1;
  private Route route;
  private RoutePath nextRoutePath;

  public RoutePath(final RoutePath routePath, RoutePath nextRoutePath) {
    route = routePath.getRoute();
    this.nextRoutePath = nextRoutePath;
  }

  public String getArrivalCity() {
    return Optional.ofNullable(nextRoutePath)
        .map(RoutePath::getArrivalCity)
        .orElse(route.getDestiny());
  }

  public Long getPathTotalTime() {
    final Long routeTime = calculateTotalTime();
    final Long nexRouteTotalTime =
        Optional.ofNullable(nextRoutePath).map(RoutePath::getPathTotalTime).orElse(0L);

    return routeTime + nexRouteTotalTime;
  }

  public Integer getTotalConnections() {

    return Optional.ofNullable(nextRoutePath).map(RoutePath::getTotalConnections).orElse(0)
        + MIN_CONNECTION;
  }

  private Long calculateTotalTime() {
    final String departureTime = route.getDepartureTime();
    final String arrivalTime = route.getArrivalTime();

    final LocalDateTime departureDate =
        LocalDateTime.of(0, 1, 1, getHours(departureTime), getMinutes(departureTime));
    final LocalDateTime arrivalDate =
        LocalDateTime.of(0, 1, 1, getHours(arrivalTime), getMinutes(arrivalTime));

    return Duration.between(departureDate, arrivalDate).toHours();
  }

  private int getHours(final String time) {

    return new Integer(time.split(":")[0]);
  }

  private int getMinutes(final String time) {
    return new Integer(time.split(":")[1]);
  }
}
