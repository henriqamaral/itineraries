package br.com.route.gateways.http;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.gateways.http.jsons.RouteResource;
import br.com.route.usecases.CreateRoute;
import br.com.route.usecases.GetRouteByCity;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RouteControllerTest {

  @InjectMocks private RouteController routeController;

  @Mock private GetRouteByCity getRouteByCity;

  @Mock private CreateRoute createRoute;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_city_name_return_list() {

    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");
    Mockito.when(getRouteByCity.execute(Mockito.anyString())).thenReturn(
        Collections.singletonList(route));

    final List<RouteResource> routesResource = routeController.getAll("Sao Paulo");

    final Route routeReturned = routesResource.get(0).toDomain();
    Assert.assertEquals(route, routeReturned);
  }

  @Test
  public void given_route_create_with_success() {
    final RouteResource route = new RouteResource("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");
    final Route routeReturned = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");
    Mockito.when(createRoute.execute(Mockito.any(Route.class))).thenReturn(routeReturned);

    final RouteResource routeResource = routeController.create(route);

    final Route routeCreated = routeResource.toDomain();
    Assert.assertEquals(routeReturned, routeCreated);

  }
}
