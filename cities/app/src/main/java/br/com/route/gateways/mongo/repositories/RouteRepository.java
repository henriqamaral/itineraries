package br.com.route.gateways.mongo.repositories;

import br.com.route.domain.Route;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {

  List<Route> findByFrom(String cityName);
}
