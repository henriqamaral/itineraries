package br.com.itinerary.gateways.http;

import br.com.itinerary.domains.ShortestWayInConnections;
import br.com.itinerary.domains.ShortestWayInTime;
import br.com.itinerary.gateways.http.jsons.ShortestWayInConnectionsResource;
import br.com.itinerary.gateways.http.jsons.ShortestWayInTimeResource;
import br.com.itinerary.usecases.CalculateShortestWayInConnections;
import br.com.itinerary.usecases.CalculateShortestWayInTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itineraries")
@Api(description = "Rest API for Itinerary")
@AllArgsConstructor
public class ItineraryController {

  private CalculateShortestWayInTime calculateShortestWayInTime;
  private CalculateShortestWayInConnections calculateShortestWayInConnections;

  @GetMapping(value = "/calculateShortestTime", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Calculate Shortest Way In Time",
      response = ShortestWayInTimeResource.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Request invalid or malformed."),
        @ApiResponse(code = 404, message = "Resource not found."),
        @ApiResponse(code = 500, message = "Internal Server or Business error.")
      })
  public ResponseEntity getShortestWayInTime(
      @RequestParam(value = "fromCity") String fromCity,
      @RequestParam(value = "destinyCity") String destinyCity) {

    final ShortestWayInTime shortestWayInTime =
        calculateShortestWayInTime.execute(fromCity, destinyCity);

    if (Objects.isNull(shortestWayInTime)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(new ShortestWayInTimeResource(shortestWayInTime));
  }

  @GetMapping(value = "/calculateShortestConnections", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(
      value = "Calculate Shortest Way In Connections",
      response = ShortestWayInConnectionsResource.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Request invalid or malformed."),
        @ApiResponse(code = 404, message = "Resource not found."),
        @ApiResponse(code = 500, message = "Internal Server or Business error.")
      })
  public ResponseEntity getShortestWayInConnections(
      @RequestParam(value = "fromCity") String fromCity,
      @RequestParam(value = "destinyCity") String destinyCity) {

    final ShortestWayInConnections shortestWayInConnections =
        calculateShortestWayInConnections.execute(fromCity, destinyCity);

    if (Objects.isNull(shortestWayInConnections)) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(new ShortestWayInConnectionsResource(shortestWayInConnections));
  }
}
