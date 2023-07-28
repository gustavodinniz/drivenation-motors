package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/customers")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    @ResponseStatus(201)
    public void createCustomer(CreateCustomerRequest createCustomerRequest) {
        customerService.createCustomer(createCustomerRequest);
    }
}
