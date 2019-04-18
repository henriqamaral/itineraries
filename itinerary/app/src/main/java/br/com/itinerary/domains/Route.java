package br.com.itinerary.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Route {

    private String from;
    private String destiny;
    private String departureTime;
    private String arrivalTime;
}
