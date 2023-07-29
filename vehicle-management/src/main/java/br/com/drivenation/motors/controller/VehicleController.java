package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.request.UpdateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByChassisNumberResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByIdResponse;
import br.com.drivenation.motors.dto.response.UpdateVehicleResponse;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
    public void createVehicle(@Valid CreateVehicleRequest createVehicleRequest) {
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


    @PUT
    @Path("/{id}")
    @ResponseStatus(200)
    public UpdateVehicleResponse updateVehicle(@PathParam("id") ObjectId id, UpdateVehicleRequest updateVehicleRequest) {
        return vehicleService.updateVehicle(id, updateVehicleRequest);
    }

    @GET
    @Path("/{id}")
    @ResponseStatus(200)
    public GetVehicleByIdResponse getVehicleById(@PathParam("id") ObjectId id) {
        return vehicleService.getVehicleById(id);
    }

    @GET
    @Path("/chassis-number/{chassisNumber}")
    @ResponseStatus(200)
    public GetVehicleByChassisNumberResponse getVehicleByChassisNumber(@PathParam("chassisNumber") String chassisNumber) {
        return vehicleService.getVehicleByChassisNumber(chassisNumber);
    }
}
