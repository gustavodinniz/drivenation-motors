package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;
import br.com.drivenation.motors.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/customers")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @ResponseStatus(200)
    public List<GetAllCustomersResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
