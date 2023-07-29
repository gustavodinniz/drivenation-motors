package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;

import java.util.List;

public interface CustomerService {

    List<GetAllCustomersResponse> getAllCustomers();
}
