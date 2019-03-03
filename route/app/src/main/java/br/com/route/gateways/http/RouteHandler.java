package br.com.route.gateways.http;

import br.com.route.gateways.http.jsons.RouteResource;
import br.com.route.usecases.CreateRoute;
import br.com.route.usecases.GetRouteByCity;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@AllArgsConstructor
public class RouteHandler {

  private final CreateRoute createRoute;
  private final GetRouteByCity getRouteByCity;

  public Mono<ServerResponse> get(final ServerRequest serverRequest) {
    return serverRequest
        .queryParam("cityName")
        .map(
            cityName ->
                ServerResponse.ok()
                    .body(
                        getRouteByCity.execute(cityName).map(RouteResource::new),
                        RouteResource.class))
        .orElse(ServerResponse.badRequest().build());
  }

  public Mono<ServerResponse> post(final ServerRequest serverRequest) {
    return serverRequest
        .bodyToMono(RouteResource.class)
        .flatMap(routeResource -> createRoute.execute(routeResource.toDomain()))
        .flatMap(
            route ->
                ServerResponse.created(URI.create("/routes/" + route.getId()))
                    .body(Mono.just(new RouteResource(route)), RouteResource.class))
        .onErrorResume(
            DuplicateKeyException.class,
            t -> ServerResponse.status(HttpStatus.BAD_REQUEST).syncBody(t.getMessage())
            );
  }
}
