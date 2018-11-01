package br.com.itinerary.route.usecases;

import br.com.itinerary.route.domain.Route;
import br.com.itinerary.route.gateways.RouteGateway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.MockitoAnnotations.initMocks;

public class GetAllRoutesTest {

  @InjectMocks private GetAllRoutes getAllRoutes;

  @Mock private RouteGateway routeGateway;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void should_return_all_routes() {

    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeGateway.getAll()).thenReturn(Flux.just(route));

    StepVerifier.create(getAllRoutes.execute())
        .assertNext(routeReturn -> Assert.assertEquals(route.getId(), routeReturn.getId()))
        .verifyComplete();
  }

  @Test
  public void should_return_empty_list() {
    Mockito.when(routeGateway.getAll()).thenReturn(Flux.empty());

    StepVerifier.create(getAllRoutes.execute()).expectNextCount(0).verifyComplete();
  }
}
