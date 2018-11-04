package br.com.route.gateways.mongo;

import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RouteMongoGatewayTest {

  @InjectMocks private RouteMongoGateway routeMongoGateway;

  @Mock private RouteRepository routeRepository;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_route_name_should_return_route() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.findByFrom(Mockito.anyString())).thenReturn(
        Collections.singletonList(route));

    Assert.assertThat(Collections.singletonList(route), is(routeMongoGateway.getByCityName("Sao Paulo")));
  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeRepository.findByFrom(Mockito.anyString()))
        .thenReturn(Collections.EMPTY_LIST);

    Assert.assertThat(Collections.EMPTY_LIST, is(routeMongoGateway.getByCityName("Sao Paulo")));
  }

  @Test
  public void given_route_create_with_success() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");
    final Route routeCreated = new Route("42342342", "Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.save(Mockito.any(Route.class))).thenReturn(routeCreated);

    final Route routeReturned = routeMongoGateway.create(route);

    Assert.assertEquals(routeCreated.getId(), routeReturned.getId());
  }

  @Test
  public void given_route_should_return_success() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");
    final Route routeCreated = new Route("42342342", "Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(
            routeRepository.findByFromAndDestinyAndDepartureTimeAndArrivalTime(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(routeCreated);

    final Route routeReturned = routeMongoGateway.findRouteByAllFields(route);

    Assert.assertEquals(routeCreated.getId(), routeReturned.getId());
  }
}
