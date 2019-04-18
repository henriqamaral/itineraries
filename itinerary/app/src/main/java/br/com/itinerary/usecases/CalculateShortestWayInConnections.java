package br.com.itinerary.usecases;

import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInConnections;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import static java.util.Comparator.comparing;

@Component
@AllArgsConstructor
public class CalculateShortestWayInConnections {

  private MountRoutPath mountRoutPath;

  public Mono<ShortestWayInConnections> execute(final String fromCity, final String toCity) {

    return MathFlux.min(
            mountRoutPath.execute(fromCity, toCity), comparing(RoutePath::getTotalConnections))
        .map(ShortestWayInConnections::new);
  }
}
