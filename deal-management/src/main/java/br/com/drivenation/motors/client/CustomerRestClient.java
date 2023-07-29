package br.com.drivenation.motors.client;

import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@ApplicationScoped
@RegisterRestClient
public interface CustomerRestClient {

    @GET
    @Path("/customers")
    List<GetAllCustomersResponse> getAllCustomers();
}
