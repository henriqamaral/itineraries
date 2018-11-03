package br.com.route.gateways.mongo;

import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

import br.com.route.domain.Route;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import java.util.Arrays;
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

    Mockito.when(routeRepository.findByFrom(Mockito.anyString()))
        .thenReturn(Arrays.asList(route));

    Assert.assertThat(Arrays.asList(route), is(routeMongoGateway.getByCityName("Sao Paulo")));

  }

  @Test
  public void given_route_name_should_return_empty() {

    Mockito.when(routeRepository.findByFrom(Mockito.anyString())).thenReturn(Collections.EMPTY_LIST);

    Assert.assertThat(Collections.EMPTY_LIST, is(routeMongoGateway.getByCityName("Sao Paulo")));
  }
}
