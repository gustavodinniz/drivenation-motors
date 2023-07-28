package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;

public interface CustomerService {

    void createCustomer(CreateCustomerRequest createCustomerRequest);
}
