package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.CustomerEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<CustomerEntity> {
}
