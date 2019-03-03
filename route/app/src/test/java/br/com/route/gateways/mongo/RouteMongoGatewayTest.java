package br.com.route.gateways.mongo;

import br.com.route.domain.Route;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.MockitoAnnotations.initMocks;

public class RouteMongoGatewayTest {

  @InjectMocks private RouteMongoGateway routeMongoGateway;

  @Mock private RouteRepository routeRepository;

  @Test
  public void given_route_create_with_success() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");
    final Route routeCreated = new Route("42342342", "Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.save(Mockito.any(Route.class)))
        .thenReturn(Mono.just(routeCreated));

    StepVerifier.create(routeMongoGateway.create(route))
        .assertNext(
            routeReturned -> Assert.assertEquals(routeReturned.getId(), routeCreated.getId()))
        .verifyComplete();
  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeRepository.findByFrom(Mockito.anyString())).thenReturn(Flux.empty());

    StepVerifier.create(routeMongoGateway.getByCityName("Sao Paulo")).verifyComplete();
  }

  @Test
  public void given_route_name_should_return_route() {
    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "", "");

    Mockito.when(routeRepository.findByFrom(Mockito.anyString())).thenReturn(Flux.just(route));

    StepVerifier.create(routeMongoGateway.getByCityName("Sao Paulo"))
        .assertNext(routeReturn -> Assert.assertEquals(route.getId(), routeReturn.getId()))
        .verifyComplete();
  }

  @Before
  public void setup() {
    initMocks(this);
  }
}
