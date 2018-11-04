package br.com.route.usecases;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.exceptions.RouteValidationException;
import br.com.route.gateways.RouteGateway;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RouteValidatorTest {

  @InjectMocks private RouteValidator routeValidator;

  @Mock private RouteGateway routeGateway;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_route_should_pass_validation_with_success() {

    final Route createRoute = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");

    Mockito.when(routeGateway.findRouteByAllFields(Mockito.any(Route.class))).thenReturn(null);

    routeValidator.execute(createRoute);

    Mockito.verify(routeGateway).findRouteByAllFields(createRoute);
  }

  @Test(expected = RouteValidationException.class)
  public void given_route_should_throw_validation_exception() {

    final Route createRoute = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");
    final Route returnedRoute = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");

    Mockito.when(routeGateway.findRouteByAllFields(Mockito.any(Route.class))).thenReturn(returnedRoute);


    routeValidator.execute(createRoute);

  }
}
