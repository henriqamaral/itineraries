package br.com.route.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Route {
  @Id private String id;
  @Indexed private String from;
  @Indexed private String destiny;
  private String departureTime;
  private String arrivalTime;

  public Route(String from, String destinyCity, String departureTime, String arrivalTime) {
    this.from = from;
    this.destiny = destinyCity;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
  }
}
