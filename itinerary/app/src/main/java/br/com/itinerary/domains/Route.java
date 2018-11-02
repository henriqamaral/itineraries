package br.com.itinerary.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Route {

    private String from;
    private String destiny;
    private String departureTime;
    private String arrivalTime;
}
