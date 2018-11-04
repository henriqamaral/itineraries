package br.com.itinerary.usecases;

import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInConnections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalculateShortestWayInConnections {

  private MountRoutPath mountRoutPath;

  public ShortestWayInConnections execute(final String fromCity, final String toCity) {

    final List<RoutePath> routePaths = mountRoutPath.execute(fromCity, toCity);

    return getShortestWayInConnections(routePaths);
  }

  private ShortestWayInConnections getShortestWayInConnections(List<RoutePath> routePaths) {

    Comparator<RoutePath> comparator = Comparator.comparing(RoutePath::getTotalConnections);

    return routePaths
        .stream()
        .filter(Objects::nonNull).min(comparator)
        .map(ShortestWayInConnections::new)
        .orElse(null);
  }
}
