package br.com.route.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@CompoundIndexes({
  @CompoundIndex(
      unique = true,
      name = "routeIndex",
      def = "{from : 1, destiny : 1, departureTime : 1, arrivalTime : 1}",
      background = true)
})
@EqualsAndHashCode(exclude = {"id"})
public class Route {
  @Id private String id;
  @Indexed private String from;
  private String destiny;
  private String departureTime;
  private String arrivalTime;

  public Route(String from, String destinyCity, String departureTime, String arrivalTime) {
    this.from = from;
    this.destiny = destinyCity;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }
}
