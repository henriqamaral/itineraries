package br.com.itinerary.gateways.http;

import br.com.itinerary.exceptions.FailCommunicationException;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import br.com.itinerary.gateways.http.jsons.ShortestWayInConnectionsResource;
import br.com.itinerary.gateways.http.jsons.ShortestWayInTimeResource;
import br.com.itinerary.usecases.CalculateShortestWayInConnections;
import br.com.itinerary.usecases.CalculateShortestWayInTime;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;
import static reactor.core.publisher.Mono.just;

@Component
@AllArgsConstructor
public class ItineraryHandler {

  private CalculateShortestWayInConnections calculateShortestWayInConnections;
  private CalculateShortestWayInTime calculateShortestWayInTime;

  public Mono<ServerResponse> getShortestConnection(final ServerRequest serverRequest) {

    final String fromCity =
        serverRequest
            .queryParam("fromCity")
            .orElseThrow(() -> new RoutesNotFoundException("From city required"));
    final String toCity =
        serverRequest
            .queryParam("toCity")
            .orElseThrow(() -> new RoutesNotFoundException("To city required"));

    return calculateShortestWayInConnections
        .execute(fromCity, toCity)
        .flatMap(
            a ->
                ok().body(
                        just(new ShortestWayInConnectionsResource(a)),
                        ShortestWayInConnectionsResource.class))
        .onErrorResume(
            RoutesNotFoundException.class,
            t -> status(HttpStatus.NOT_FOUND).syncBody(t.getMessage()))
        .onErrorResume(
            FailCommunicationException.class,
            t -> {
              System.out.println(t.getMessage());
              return status(HttpStatus.INTERNAL_SERVER_ERROR).syncBody(t.getMessage());
            })
        .switchIfEmpty(notFound().build());
  }

  public Mono<ServerResponse> getShortestTime(final ServerRequest serverRequest) {

    final String fromCity =
        serverRequest
            .queryParam("fromCity")
            .orElseThrow(() -> new RoutesNotFoundException("From city required"));
    final String toCity =
        serverRequest
            .queryParam("toCity")
            .orElseThrow(() -> new RoutesNotFoundException("To city required"));

    return calculateShortestWayInTime
        .execute(fromCity, toCity)
        .flatMap(
            a -> ok().body(just(new ShortestWayInTimeResource(a)), ShortestWayInTimeResource.class))
        .onErrorResume(
            RoutesNotFoundException.class,
            t -> status(HttpStatus.NOT_FOUND).syncBody(t.getMessage()))
        .onErrorResume(
            FailCommunicationException.class,
            t -> {
              System.out.println(t.getMessage());
              return status(HttpStatus.INTERNAL_SERVER_ERROR).syncBody(t.getMessage());
            })
        .switchIfEmpty(notFound().build());
  } // FailCommunicationException
}
