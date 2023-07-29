package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.entity.MaintenanceEntity;
import br.com.drivenation.motors.enumeration.RequesterType;
import br.com.drivenation.motors.enumeration.VehicleStatus;
import br.com.drivenation.motors.repository.MaintenanceRepository;
import br.com.drivenation.motors.repository.VehicleRepository;
import br.com.drivenation.motors.service.MaintenanceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class MaintenanceServiceImpl implements MaintenanceService {

    @Inject
    MaintenanceRepository maintenanceRepository;

    @Inject
    VehicleRepository vehicleRepository;

    @Override
    public void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        log.info("Creating maintenance for vehicle with chassis number: {}", createMaintenanceRequest.getVehicleChassisNumber());
        maintenanceRepository.persist(MaintenanceEntity.valueOf(createMaintenanceRequest));
        log.info("Maintenance created.");

        updateVehicleStatus(createMaintenanceRequest);
    }

    private void updateVehicleStatus(CreateMaintenanceRequest createMaintenanceRequest) {
        if (createMaintenanceRequest.getRequesterType().equals(RequesterType.DEALERSHIP)) {
            log.info("Maintenance requested by the dealership.");
            log.info("Updating vehicle status with chassis number {}. Status: {}", createMaintenanceRequest.getVehicleChassisNumber(), VehicleStatus.MAINTENANCE);
            vehicleRepository.findByChassisNumber(createMaintenanceRequest.getVehicleChassisNumber()).ifPresentOrElse(
                    vehicle -> {
                        vehicle.setStatus(VehicleStatus.MAINTENANCE);
                        vehicleRepository.update(vehicle);
                        log.info("Vehicle status updated.");
                    },
                    () -> log.error("Vehicle with chassis number {} not found.", createMaintenanceRequest.getVehicleChassisNumber()));
        }
    }
}
