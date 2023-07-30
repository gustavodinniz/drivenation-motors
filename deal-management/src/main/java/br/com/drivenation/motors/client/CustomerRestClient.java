package br.com.drivenation.motors.client;

import br.com.drivenation.motors.dto.response.GetAllCustomersResponse;
import br.com.drivenation.motors.dto.response.GetCustomerByDocumentResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@ApplicationScoped
@RegisterRestClient
public interface CustomerRestClient {

    @GET
    @Path("/customers")
    List<GetAllCustomersResponse> getAllCustomers();

    @GET
    @Path("/customers/document/{document}")
    GetCustomerByDocumentResponse getCustomerByDocument(@PathParam("document") String document);
}
