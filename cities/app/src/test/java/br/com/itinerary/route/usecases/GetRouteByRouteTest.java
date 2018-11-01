package br.com.itinerary.route.usecases;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.RouteGateway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.MockitoAnnotations.initMocks;

public class GetRouteByRouteTest {

  @InjectMocks private GetRouteByCity getRouteByCity;

  @Mock private RouteGateway routeGateway;

    @Before
    public void setup() {
        initMocks(this);
    }

  @Test
  public void given_route_name_should_return_route() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeGateway.getByCityName(Mockito.anyString())).thenReturn(Mono.just(route));

    StepVerifier.create(getRouteByCity.execute("Sao Paulo"))
        .assertNext(routeReturn -> Assert.assertEquals(route.getId(), routeReturn.getId()))
        .verifyComplete();
  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeGateway.getByCityName(Mockito.anyString())).thenReturn(Mono.empty());

    StepVerifier.create(getRouteByCity.execute("Sao Paulo")).verifyComplete();
  }
}
