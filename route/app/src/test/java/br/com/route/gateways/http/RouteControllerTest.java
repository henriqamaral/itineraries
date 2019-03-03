package br.com.route.gateways.http;

import br.com.route.domain.Route;
import br.com.route.gateways.http.jsons.RouteResource;
import br.com.route.gateways.mongo.repositories.RouteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouteControllerTest {

  @Autowired RouteRepository routeRepository;
  @Autowired WebTestClient webTestClient;

  @Test
  public void given_city_name_return_list() {

    final Route route = new Route("Sao Paulo", "Rio de Janeiro", "11:00", "12:00");

    routeRepository.save(route).block();

    webTestClient
        .get()
        .uri("routes?cityName=Sao Paulo")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("@.[0].from")
        .isEqualTo("Sao Paulo")
        .jsonPath("@.[0].destiny")
        .isEqualTo("Rio de Janeiro");
  }

  @Test
  public void given_route_create_with_success() {
    final RouteResource route = new RouteResource("Macapa", "Rio de Janeiro", "11:00", "12:00");

    webTestClient
        .post()
        .uri("/routes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(route), RouteResource.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.from")
        .isEqualTo("Macapa");
  }

  @Test
  public void given_wrong_city_name_return_empty_list() {

    webTestClient
        .get()
        .uri("routes?cityName=Sao Paolo")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(RouteResource.class)
        .hasSize(0);
  }

  @Test
  public void given_route_already_exists_return_error() {


    routeRepository.save(new Route("Rio de Janeiro", "Curitiba", "11:00", "12:00")).block();

    final RouteResource route = new RouteResource("Rio de Janeiro", "Curitiba", "11:00", "12:00");

    webTestClient
        .post()
        .uri("/routes")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(route), RouteResource.class)
        .exchange()
        .expectStatus()
        .is4xxClientError();
  }
}
