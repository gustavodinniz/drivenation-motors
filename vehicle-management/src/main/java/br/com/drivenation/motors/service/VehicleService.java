package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.request.UpdateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByIdResponse;
import br.com.drivenation.motors.dto.response.UpdateVehicleResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface VehicleService {

    void createVehicle(CreateVehicleRequest createVehicleRequest);

    List<GetAllVehicleResponse> getAllVehicles();

    void deleteVehicle(ObjectId id);

    UpdateVehicleResponse updateVehicle(ObjectId id, UpdateVehicleRequest updateVehicleRequest);

    GetVehicleByIdResponse getVehicleById(ObjectId id);
}
