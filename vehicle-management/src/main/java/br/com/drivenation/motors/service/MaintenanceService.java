package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.dto.request.VehicleEventRequest;

public interface MaintenanceService {

    void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest);

    void receiveMaintenance(VehicleEventRequest vehicleEventRequest);
}
