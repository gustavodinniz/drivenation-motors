package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.DealEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DealRepository implements PanacheMongoRepository<DealEntity> {
}
