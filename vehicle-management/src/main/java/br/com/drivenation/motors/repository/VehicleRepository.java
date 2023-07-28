package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.VehicleEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class VehicleRepository implements PanacheMongoRepository<VehicleEntity> {

    public Optional<VehicleEntity> findByChassisNumber(String chassisNumber) {
        return find("chassisNumber", chassisNumber).firstResultOptional();
    }
}
