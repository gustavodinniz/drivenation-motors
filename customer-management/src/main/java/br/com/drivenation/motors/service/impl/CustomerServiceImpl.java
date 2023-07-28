package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.entity.CustomerEntity;
import br.com.drivenation.motors.repository.CustomerRepository;
import br.com.drivenation.motors.service.CustomerService;
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
        log.info("Creating customer: {}", createCustomerRequest.getFirstName());
        customerRepository.persist(CustomerEntity.valueOf(createCustomerRequest));
        log.info("Customer created.");
    }
}
