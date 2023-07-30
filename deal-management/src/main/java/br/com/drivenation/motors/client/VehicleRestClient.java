package br.com.drivenation.motors.client;

import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByChassisNumberResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@ApplicationScoped
@RegisterRestClient
public interface VehicleRestClient {

    @GET
    @Path("/vehicles")
    List<GetAllVehicleResponse> getAllVehicles();

    @GET
    @Path("vehicles/chassis-number/{chassisNumber}")
    GetVehicleByChassisNumberResponse getVehicleByChassisNumber(@PathParam("chassisNumber") String chassisNumber);
}
