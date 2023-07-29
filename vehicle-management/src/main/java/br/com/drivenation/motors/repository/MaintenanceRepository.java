package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.MaintenanceEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MaintenanceRepository implements PanacheMongoRepository<MaintenanceEntity> {
}
