package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;

public interface VehicleService {

    void createVehicle(CreateVehicleRequest createVehicleRequest);
}
