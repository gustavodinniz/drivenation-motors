package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;

import java.util.List;

public interface VehicleService {

    List<GetAllVehicleResponse> getAllVehicles();
}
