package br.com.itinerary.gateways.http.router.integration.jsons;

import br.com.itinerary.domains.Route;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteResource {

  private String from;
  private String destiny;
  private String departureTime;
  private String arrivalTime;

  public Route toDomain() {
    return new Route(from, destiny, departureTime, arrivalTime);
  }

}
