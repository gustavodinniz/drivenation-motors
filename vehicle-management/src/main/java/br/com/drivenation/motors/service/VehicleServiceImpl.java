package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.repository.VehicleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class VehicleServiceImpl implements VehicleService {

    @Inject
    VehicleRepository vehicleRepository;

    @Override
    public void createVehicle(CreateVehicleRequest createVehicleRequest) {
        log.info("Creating vehicle: {}", createVehicleRequest);
        vehicleRepository.persist(createVehicleRequest.toEntity());
        log.info("Vehicle created successfully");
    }
}
