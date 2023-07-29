package br.com.drivenation.motors.client;

import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@ApplicationScoped
@RegisterRestClient
public interface VehicleRestClient {

    @GET
    @Path("/vehicles")
    List<GetAllVehicleResponse> getAllVehicles();
}
