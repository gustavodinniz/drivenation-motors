package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.entity.CustomerEntity;
import br.com.drivenation.motors.exception.ConflictException;
import br.com.drivenation.motors.repository.CustomerRepository;
import br.com.drivenation.motors.service.CustomerService;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Override
    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        try {
            log.info("Creating customer: {}", createCustomerRequest.getFirstName());
            customerRepository.persist(CustomerEntity.valueOf(createCustomerRequest));
            log.info("Customer created.");
        } catch (MongoWriteException e) {
            if (e.getError().getCategory() == ErrorCategory.DUPLICATE_KEY) {
                log.error("A customer with document {} already exists.", createCustomerRequest.getDocument());
                throw new ConflictException("A customer with document already exists.");
            }
        }
    }
}
