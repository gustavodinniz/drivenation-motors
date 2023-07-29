package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;
import br.com.drivenation.motors.dto.response.GetCustomerByDocumentResponse;

import java.util.List;

public interface CustomerService {

    void createCustomer(CreateCustomerRequest createCustomerRequest);

    GetCustomerByDocumentResponse getCustomerByDocument(String document);

    List<GetAllCustomersResponse> getAllCustomers();

}
