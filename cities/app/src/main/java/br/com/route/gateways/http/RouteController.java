package br.com.route.gateways.http;

import static org.springframework.http.HttpStatus.OK;

import br.com.route.gateways.http.jsons.RouteResource;
import br.com.route.usecases.GetRouteByCity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
  public List<RouteResource> get(@RequestParam(value = "cityName") final String cityName) {
    return getRouteByCity
        .execute(cityName)
        .stream()
        .map(RouteResource::new)
        .collect(Collectors.toList());
  }
}
