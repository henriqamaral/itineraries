package br.com.route.gateways.mongo.repositories;

import br.com.route.domain.Route;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RouteRepository extends ReactiveMongoRepository<Route, String> {

  Flux<Route> findByFrom(String cityName);

}
