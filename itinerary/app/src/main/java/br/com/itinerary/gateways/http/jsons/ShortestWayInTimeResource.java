package br.com.itinerary.gateways.http.jsons;

import br.com.itinerary.domains.ShortestWayInTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortestWayInTimeResource {

  private String departureCity;
  private String arrivalCity;
  private Long totalHours;

  public ShortestWayInTimeResource(final ShortestWayInTime shortestWayInTime) {
    departureCity = shortestWayInTime.getDepartureCity();
    arrivalCity = shortestWayInTime.getArrivalCity();
    totalHours = shortestWayInTime.getTotalHours();
  }
}
