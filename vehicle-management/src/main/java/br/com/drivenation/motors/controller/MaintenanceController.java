package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.service.MaintenanceService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/maintenances")
public class MaintenanceController {

    @Inject
    MaintenanceService maintenanceService;

    @POST
    @ResponseStatus(201)
    public void createMaintenance(@Valid CreateMaintenanceRequest createMaintenanceRequest) {
        maintenanceService.createMaintenance(createMaintenanceRequest);
    }
}
