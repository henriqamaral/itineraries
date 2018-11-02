package br.com.itinerary.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortestWayInTime {

  private String departureCity;
  private String arrivalCity;
  private Long totalHours;

  public ShortestWayInTime(RoutePath routePath) {

    totalHours = routePath.getPathTotalTime();
    departureCity = routePath.getRoute().getFrom();
    arrivalCity = routePath.getArrivalCity();
  }
}
