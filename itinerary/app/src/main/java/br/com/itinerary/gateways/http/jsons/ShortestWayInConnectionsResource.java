package br.com.itinerary.gateways.http.jsons;

import br.com.itinerary.domains.ShortestWayInConnections;
import br.com.itinerary.domains.ShortestWayInTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortestWayInConnectionsResource {

  private String departureCity;
  private String arrivalCity;
  private Integer totalConnections;

  public ShortestWayInConnectionsResource(final ShortestWayInConnections shortestWayInConnections) {
    departureCity = shortestWayInConnections.getDepartureCity();
    arrivalCity = shortestWayInConnections.getArrivalCity();
    totalConnections = shortestWayInConnections.getTotalConnections();
  }
}
