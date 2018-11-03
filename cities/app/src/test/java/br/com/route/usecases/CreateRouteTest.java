package br.com.route.usecases;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.exceptions.RouteValidationException;
import br.com.route.gateways.RouteGateway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CreateRouteTest {

  @InjectMocks private CreateRoute createRoute;

  @Mock private RouteGateway routeGateway;

  @Mock private RouteValidator routeValidator;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_route_create_with_success() {

    final Route route = new Route("341241233", "Sao Paulo", "Rio de Janeiro", "11:00", "12:00");

    Mockito.when(routeGateway.create(Mockito.any(Route.class))).thenReturn(route);

    final Route routeReturned = createRoute.execute(route);

    Assert.assertEquals(route.getId(), routeReturned.getId());
  }

  @Test(expected = RouteValidationException.class)
  public void given_route_should_throw_validation_exception() {

    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");

    Mockito.doThrow(RouteValidationException.class)
        .when(routeValidator)
        .execute(Mockito.any(Route.class));

    createRoute.execute(route);
  }
}
