package br.com.route.gateways.http;

import static org.springframework.http.HttpStatus.OK;

import br.com.route.domain.Route;
import br.com.route.gateways.http.jsons.RouteResource;
import br.com.route.usecases.CreateRoute;
import br.com.route.usecases.GetRouteByCity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
@Api(value = "Routes", description = "Rest API for Route")
@AllArgsConstructor
public class RouteController {

  private final GetRouteByCity getRouteByCity;

  private final CreateRoute createRoute;

  @GetMapping
  @ResponseStatus(OK)
  @ApiOperation(value = "Get routes by filter", response = RouteResource.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Routes found"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Resource not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  public List<RouteResource> getAll(@RequestParam(value = "cityName") final String cityName) {
    return getRouteByCity
        .execute(cityName)
        .stream()
        .map(RouteResource::new)
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(OK)
  @ApiOperation(value = "Create route", response = RouteResource.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Resource not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  public RouteResource create(@RequestBody @Valid final RouteResource route) {
    final Route routeCreated = createRoute.execute(route.toDomain());
    return new RouteResource(routeCreated);
  }
}
