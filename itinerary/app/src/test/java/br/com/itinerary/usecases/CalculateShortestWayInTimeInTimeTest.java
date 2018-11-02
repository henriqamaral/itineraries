package br.com.itinerary.usecases;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.ShortestWayInTime;
import br.com.itinerary.gateways.RoutesGateway;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CalculateShortestWayInTimeInTimeTest {

  @InjectMocks private CalculateShortestWayInTime calculateShortestWayInTime;

  @Mock private RoutesGateway routesGateway;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_two_cities_calculate_the_itinerary_with_success() {

    final Route routeOne = new Route("Sao Paulo", "Macapa", "9:00", "11:00");
    final Route routeFive = new Route("Macapa", "Rio de Janeiro", "9:00", "11:00");
    final Route routeTwo = new Route("Rio de Janeiro", "Salvador", "12:00", "16:00");
    final Route routeThree = new Route("Sao Paulo", "Salvador", "9:00", "19:00");

    Mockito.when(routesGateway.findRouteByFromCityName("Sao Paulo"))
        .thenReturn(new ArrayList<>(Arrays.asList(routeOne, routeThree)));
    Mockito.when(routesGateway.findRouteByFromCityName("Macapa"))
        .thenReturn(new ArrayList<>(Collections.singletonList(routeFive)));
    Mockito.when(routesGateway.findRouteByFromCityName("Rio de Janeiro"))
        .thenReturn(new ArrayList<>(Collections.singletonList(routeTwo)));

    final ShortestWayInTime shortestWayInTime =
        calculateShortestWayInTime.execute("Sao Paulo", "Salvador");

    Assert.assertEquals(new Long(8L), shortestWayInTime.getTotalHours());
  }

  @Test
  public void given_two_cities_fail_when_not_find_destiny_city() {

    final Route routeOne = new Route("Sao Paulo", "Macapa", "9:00", "11:00");

    Mockito.when(routesGateway.findRouteByFromCityName("Sao Paulo"))
        .thenReturn(new ArrayList<>(Collections.singletonList(routeOne)));

    final ShortestWayInTime shortestWayInTime =
        calculateShortestWayInTime.execute("Sao Paulo", "Milan");

    Assert.assertNull(shortestWayInTime);
  }

  @Test(expected = RuntimeException.class)
  public void given_two_cities_fail_when_not_find_departure_city() {

    calculateShortestWayInTime.execute("Sao Paulo", "Milan");
  }
}
