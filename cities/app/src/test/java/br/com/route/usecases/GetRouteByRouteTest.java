package br.com.route.usecases;

import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.gateways.RouteGateway;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

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

    Mockito.when(routeGateway.getByCityName(Mockito.anyString())).thenReturn(
        Collections.singletonList(route));

    Assert.assertThat(Collections.singletonList(route), is(getRouteByCity.execute("Sao Paulo")));
  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeGateway.getByCityName(Mockito.anyString()))
        .thenReturn(Collections.EMPTY_LIST);

    Assert.assertThat(Collections.EMPTY_LIST, is(getRouteByCity.execute("Sao Paulo")));
  }
}
