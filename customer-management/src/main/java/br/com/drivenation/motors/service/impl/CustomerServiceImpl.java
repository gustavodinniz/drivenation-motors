package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.dto.response.GetCustomerByDocumentResponse;
import br.com.drivenation.motors.entity.CustomerEntity;
import br.com.drivenation.motors.exception.ConflictException;
import br.com.drivenation.motors.repository.CustomerRepository;
import br.com.drivenation.motors.service.CustomerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Override
    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        log.info("Creating customer with document: {}", createCustomerRequest.getDocument());
        customerRepository.findByDocument(createCustomerRequest.getDocument()).ifPresent(customer -> {
            log.error("A customer with document {} already exists.", createCustomerRequest.getDocument());
            throw new ConflictException("A customer with document already exists.");
        });
        customerRepository.persist(CustomerEntity.valueOf(createCustomerRequest));
        log.info("Customer created.");
    }

    @Override
    public GetCustomerByDocumentResponse getCustomerByDocument(String document) {
        log.info("Finding customer by document: {}", document);
        CustomerEntity customerEntity = customerRepository.findByDocument(document).orElseThrow(() -> {
            log.error("Customer not found.");
            return new NotFoundException();
        });

        log.info("Customer found.");
        return GetCustomerByDocumentResponse.valueOf(customerEntity);
    }
}
