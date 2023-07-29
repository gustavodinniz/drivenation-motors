package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.dto.response.GetCustomerByDocumentResponse;

public interface CustomerService {

    void createCustomer(CreateCustomerRequest createCustomerRequest);

    GetCustomerByDocumentResponse getCustomerByDocument(String document);
}
