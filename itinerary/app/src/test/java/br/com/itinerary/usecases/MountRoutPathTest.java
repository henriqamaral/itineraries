package br.com.itinerary.usecases;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.RoutesGateway;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.MockitoAnnotations.initMocks;

public class MountRoutPathTest {

  @InjectMocks private MountRoutPath mountRoutPath;
  @Mock private RoutesGateway routesGateway;

  @Test
  public void given_two_cities_fail_when_not_find_departure_city() {

    Mockito.when(routesGateway.findRouteByFromCityName("Milan")).thenReturn(Flux.empty());

    final Flux<RoutePath> routePaths = mountRoutPath.execute("Sao Paulo", "Milan");
    StepVerifier.create(routePaths).expectError(RoutesNotFoundException.class).verify();
  }

  @Test
  public void given_two_cities_fail_when_not_find_destiny_city() {

    Mockito.when(routesGateway.findRouteByFromCityName("Milan"))
        .thenReturn(Flux.empty());

    final Flux<RoutePath> routePaths = mountRoutPath.execute("Sao Paulo", "Milan");
    StepVerifier.create(routePaths).expectError(RoutesNotFoundException.class).verify();
  }

  @Test
  public void given_two_cities_mount_path_with_success() {

    final Route routeOne = new Route("Sao Paulo", "Macapa", "9:00", "11:00");
    final Route routeFive = new Route("Macapa", "Rio de Janeiro", "9:00", "11:00");
    final Route routeTwo = new Route("Rio de Janeiro", "Salvador", "12:00", "16:00");
    final Route routeThree = new Route("Sao Paulo", "Salvador", "9:00", "19:00");

    Mockito.when(routesGateway.findRouteByFromCityName("Salvador"))
        .thenReturn(Flux.just(routeTwo, routeThree));
    Mockito.when(routesGateway.findRouteByFromCityName("Rio de Janeiro"))
        .thenReturn(Flux.just((routeFive)));
    Mockito.when(routesGateway.findRouteByFromCityName("Macapa"))
        .thenReturn(Flux.just((routeOne)));

    final Flux<RoutePath> routePaths = mountRoutPath.execute("Sao Paulo", "Salvador");

    Predicate<RoutePath> p = route -> (route.getArrivalCity().equalsIgnoreCase("Salvador"));

    StepVerifier.create(routePaths).expectNextMatches(p).expectNextMatches(p).verifyComplete();
    StepVerifier.create(routePaths).expectNextCount(2).verifyComplete();
  }

  @Before
  public void setup() {
    initMocks(this);
  }
}
