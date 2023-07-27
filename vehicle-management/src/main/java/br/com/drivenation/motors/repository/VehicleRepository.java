package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.VehicleEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VehicleRepository implements PanacheMongoRepository<VehicleEntity> {
}
