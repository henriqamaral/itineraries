package br.com.itinerary.usecases;

import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.RoutesGateway;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MountRoutPathTest {

  @InjectMocks private MountRoutPath mountRoutPath;
  @Mock private RoutesGateway routesGateway;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_two_cities_mount_path_with_success() {

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

    final List<RoutePath> routePaths = mountRoutPath.execute("Sao Paulo", "Salvador");

    Assert.assertThat(2, is(routePaths.size()));
  }

  @Test(expected = RoutesNotFoundException.class)
  public void given_two_cities_fail_when_not_find_departure_city() {

    mountRoutPath.execute("Sao Paulo", "Milan");
  }
}
