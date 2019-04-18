package br.com.itinerary.gateways.http.router;

import br.com.itinerary.domains.Route;
import br.com.itinerary.exceptions.FailCommunicationException;
import br.com.itinerary.gateways.RoutesGateway;
import br.com.itinerary.gateways.http.router.integration.jsons.RouteResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RoutesClientGateway implements RoutesGateway {

  private final WebClient webClient;

  public RoutesClientGateway(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
  }

  @Override
  public Flux<Route> findRouteByFromCityName(final String fromCity) {

    final Mono<Throwable> failedToCalculate =
        Mono.error(new FailCommunicationException("Failed to calculate"));

    return webClient
        .get()
        .uri("/routes?cityName={cityName}" , fromCity)
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse -> failedToCalculate)
        .onStatus(HttpStatus::is5xxServerError, clientResponse -> failedToCalculate)
        .bodyToFlux(RouteResource.class)
        .map(RouteResource::toDomain);
  }
}
