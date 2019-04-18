package br.com.itinerary.usecases;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInConnections;
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

public class CalculateShortestWayInTimeInConnectionsTest {

  @InjectMocks private CalculateShortestWayInConnections calculateShortestWayInConnections;
  @Mock private MountRoutPath mountRoutPath;

  @Test
  public void given_two_cities_calculate_the_itinerary_with_success() {

    Mockito.when(mountRoutPath.execute(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(mountPaths());

    final Mono<ShortestWayInConnections> shortestWayInConnections =
        calculateShortestWayInConnections.execute("Sao Paulo", "Salvador");

    StepVerifier.create(shortestWayInConnections)
        .assertNext(t -> Assert.assertEquals(new Integer(1), t.getTotalConnections()))
        .verifyComplete();
  }

  private Flux<RoutePath> mountPaths() {

    final Route routeOne = new Route("Sao Paulo", "Macapa", "9:00", "11:00");
    final Route routeFive = new Route("Macapa", "Rio de Janeiro", "9:00", "11:00");
    final Route routeTwo = new Route("Rio de Janeiro", "Salvador", "12:00", "16:00");
    final Route routeThree = new Route("Sao Paulo", "Salvador", "9:00", "19:00");

    RoutePath lastPath = new RoutePath(routeTwo);
    RoutePath middlePath = new RoutePath(routeFive, lastPath);
    RoutePath firstPath = new RoutePath(routeOne, middlePath);

    return Flux.just(firstPath, new RoutePath(routeThree));
  }

  @Test
  public void given_two_cities_fail_when_not_find_destiny_city() {

    Mockito.when(mountRoutPath.execute(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Flux.empty());

    final Mono<ShortestWayInConnections> shortestWayInConnections =
        calculateShortestWayInConnections.execute("Sao Paulo", "Milan");

    StepVerifier.create(shortestWayInConnections).expectComplete().verify();

    //        Assert.assertNull(shortestWayInConnections);
  }

  @Before
  public void setup() {
    initMocks(this);
  }
}
