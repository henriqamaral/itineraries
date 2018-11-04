package br.com.itinerary.usecases;

import br.com.itinerary.domains.RoutePath;
import br.com.itinerary.domains.ShortestWayInTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalculateShortestWayInTime {

  private MountRoutPath mountRoutPath;

  public ShortestWayInTime execute(final String fromCity, final String toCity) {

    final List<RoutePath> routePaths = mountRoutPath.execute(fromCity, toCity);

    return getShortestWayTime(routePaths);
  }

  private ShortestWayInTime getShortestWayTime(List<RoutePath> routePaths) {

    Comparator<RoutePath> comparator = Comparator.comparing(RoutePath::getPathTotalTime);

    return routePaths
        .stream()
        .filter(Objects::nonNull).min(comparator)
        .map(ShortestWayInTime::new)
        .orElse(null);
  }
}
