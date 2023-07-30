package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateDealRequest;
import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.service.DealService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/deals")
public class DealController {

    @Inject
    DealService dealService;

    @POST
    @ResponseStatus(201)
    public void createDeal(CreateDealRequest createDealRequest) {
        dealService.createDeal(createDealRequest);
    }

    @POST
    @Path("/maintenance")
    @ResponseStatus(201)
    public void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        dealService.createMaintenance(createMaintenanceRequest);
    }
}
