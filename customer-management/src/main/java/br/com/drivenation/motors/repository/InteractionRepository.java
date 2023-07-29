package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.InteractionEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InteractionRepository implements PanacheMongoRepository<InteractionEntity> {
}
