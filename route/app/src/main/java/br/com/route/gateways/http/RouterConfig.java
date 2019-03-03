package br.com.route.gateways.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

  @Bean
  public RouterFunction<ServerResponse> routerFunction(final RouteHandler routeHandler) {
    return nest(
        path("/routes"),
        nest(
                accept(APPLICATION_JSON),
                route(GET(""), routeHandler::get))
            .andRoute(POST("/").and(contentType(APPLICATION_JSON)), routeHandler::post));
  }

}
