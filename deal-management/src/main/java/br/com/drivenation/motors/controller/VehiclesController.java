package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/vehicles")
public class VehiclesController {

    @Inject
    VehicleService vehicleService;

    @GET
    @ResponseStatus(200)
    public List<GetAllVehicleResponse> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}
