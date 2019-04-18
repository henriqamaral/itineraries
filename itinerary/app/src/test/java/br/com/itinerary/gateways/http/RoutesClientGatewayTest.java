package br.com.itinerary.gateways.http;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.itinerary.domains.Route;
import br.com.itinerary.gateways.http.router.integration.jsons.RouteResource;
import br.com.itinerary.gateways.http.router.RoutesClientGateway;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RoutesClientGatewayTest {

//  @InjectMocks private RoutesClientGateway routesClientGateway;

//  @Mock private RouterIntegration routerIntegration;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_city_name_should_return_with_success() {

//    final RouteResource routeResource = new RouteResource("Sao Paulo", "Macapa", "9:00", "11:00");
//    Mockito.when(routerIntegration.findRoutes("Sao Paulo"))
//        .thenReturn(Arrays.asList(routeResource));
//
//    final List<Route> routes = routesClientGateway.findRouteByFromCityName("Sao Paulo");
//
//    Assert.assertTrue(routes.size() > 0);
  }
}
