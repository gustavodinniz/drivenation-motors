package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/vehicles")
public class VehicleController {

    @Inject
    VehicleService vehicleService;

    @POST
    @ResponseStatus(201)
    public void createVehicle(CreateVehicleRequest createVehicleRequest) {
        vehicleService.createVehicle(createVehicleRequest);
    }

    @GET
    @ResponseStatus(200)
    public List<GetAllVehicleResponse> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(204)
    public void deleteVehicle(@PathParam("id") ObjectId id) {
        vehicleService.deleteVehicle(id);
    }
}
