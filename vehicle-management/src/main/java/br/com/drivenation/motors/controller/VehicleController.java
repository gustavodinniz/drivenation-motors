package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/vehicles")
public class VehicleController {

    @Inject
    VehicleService vehicleService;

    @POST
    @ResponseStatus(201)
    public void createVehicle(CreateVehicleRequest createVehicleRequest) {
        vehicleService.createVehicle(createVehicleRequest);
    }
}
