package br.com.itinerary.route.gateways.mongo.repositories;

import br.com.itinerary.route.domain.Route;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RouteRepository extends ReactiveMongoRepository<Route, String> {

    Mono<Route> findByCity(String cityName);
}
