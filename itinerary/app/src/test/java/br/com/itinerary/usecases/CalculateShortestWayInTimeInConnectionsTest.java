package br.com.itinerary.usecases;

import static org.mockito.MockitoAnnotations.initMocks;

import br.com.itinerary.domains.Route;
import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInConnections;
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

public class CalculateShortestWayInTimeInConnectionsTest {

  @InjectMocks private CalculateShortestWayInConnections calculateShortestWayInConnections;
  @Mock private MountRoutPath mountRoutPath;

  @Before
  public void setup() {
    initMocks(this);
  }

  @Test
  public void given_two_cities_calculate_the_itinerary_with_success() {

    Mockito.when(mountRoutPath.execute(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(mountPaths());

    final ShortestWayInConnections shortestWayInConnections =
        calculateShortestWayInConnections.execute("Sao Paulo", "Salvador");

    Assert.assertEquals(new Integer(1), shortestWayInConnections.getTotalConnections());
  }

  @Test
  public void given_two_cities_fail_when_not_find_destiny_city() {

    Mockito.when(mountRoutPath.execute(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Collections.emptyList());

    final ShortestWayInConnections shortestWayInConnections =
        calculateShortestWayInConnections.execute("Sao Paulo", "Milan");

    Assert.assertNull(shortestWayInConnections);
  }

  private List<RoutePath> mountPaths() {

    final Route routeOne = new Route("Sao Paulo", "Macapa", "9:00", "11:00");
    final Route routeFive = new Route("Macapa", "Rio de Janeiro", "9:00", "11:00");
    final Route routeTwo = new Route("Rio de Janeiro", "Salvador", "12:00", "16:00");
    final Route routeThree = new Route("Sao Paulo", "Salvador", "9:00", "19:00");

    RoutePath lastPath = new RoutePath(routeTwo);
    RoutePath middlePath = new RoutePath(routeFive, lastPath);
    RoutePath firstPath = new RoutePath(routeOne, middlePath);

    return new ArrayList<>(Arrays.asList(firstPath, new RoutePath(routeThree)));
  }
}
