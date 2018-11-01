package br.com.itinerary.route.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Route {
    @Id
    private String id;
    @Indexed
    private String city;
    private String destinyCity;
    private String departureTime;
    private String arrivalTime;

    public Route(String city, String destinyCity, String departureTime, String arrivalTime) {
        this.city = city;
        this.destinyCity = destinyCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
