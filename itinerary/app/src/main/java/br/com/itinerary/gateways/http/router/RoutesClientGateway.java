package br.com.itinerary.gateways.http.router;

import br.com.itinerary.domains.Route;
import br.com.itinerary.exceptions.FailCommunicationException;
import br.com.itinerary.gateways.RoutesGateway;
import br.com.itinerary.gateways.http.router.integration.RouterIntegration;
import br.com.itinerary.gateways.http.router.integration.jsons.RouteResource;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoutesClientGateway implements RoutesGateway {

  private RouterIntegration routerIntegration;

  @Override
  public List<Route> findRouteByFromCityName(final String fromCity) {

    try {
      return routerIntegration
          .findRoutes(fromCity)
          .stream()
          .map(RouteResource::toDomain)
          .collect(Collectors.toList());
    } catch (HystrixRuntimeException e) {
      throw new FailCommunicationException("Failed to calculate");
    }
  }
}
