package br.com.drivenation.motors.repository;

import br.com.drivenation.motors.entity.CustomerEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CustomerRepository implements PanacheMongoRepository<CustomerEntity> {

    public Optional<CustomerEntity> findByDocument(String document) {
        return find("document", document).firstResultOptional();
    }
}
