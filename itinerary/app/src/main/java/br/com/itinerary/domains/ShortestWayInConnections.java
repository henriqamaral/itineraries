package br.com.itinerary.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortestWayInConnections {

  private String departureCity;
  private String arrivalCity;
  private Integer totalConnections;

  public ShortestWayInConnections(RoutePath routePath) {

    totalConnections = routePath.getTotalConnections();
    departureCity = routePath.getRoute().getFrom();
    arrivalCity = routePath.getArrivalCity();
  }
}
