package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.client.CustomerRestClient;
import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;
import br.com.drivenation.motors.exception.BadRequestException;
import br.com.drivenation.motors.service.CustomerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Slf4j
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    @RestClient
    CustomerRestClient customerRestClient;

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        try {
            log.info("Retrieving all customers...");
            return customerRestClient.getAllCustomers();
        } catch (Exception e) {
            log.error("Error retrieving all customers: {}", e.getMessage());
            throw new BadRequestException("Error retrieving all customers.");
        }
    }
}
