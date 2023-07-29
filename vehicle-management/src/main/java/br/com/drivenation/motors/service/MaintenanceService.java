package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;

public interface MaintenanceService {

    void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest);
}
