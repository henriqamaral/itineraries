package br.com.route.gateways.http.jsons;

import br.com.route.domain.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RouteResource {

  private String from;
  private String destiny;
  private String departureTime;
  private String arrivalTime;

  public RouteResource(final Route route) {
    this.from = route.getFrom();
    this.destiny = route.getDestiny();
    this.departureTime = route.getDepartureTime();
    this.arrivalTime = route.getArrivalTime();
  }
}
