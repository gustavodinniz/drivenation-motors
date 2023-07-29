package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.client.VehicleRestClient;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.exception.BadRequestException;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Slf4j
@ApplicationScoped
public class VehicleServiceImpl implements VehicleService {

    @Inject
    @RestClient
    VehicleRestClient vehicleRestClient;

    @Override
    public List<GetAllVehicleResponse> getAllVehicles() {
        try {
            log.info("Retrieving all vehicles...");
            return vehicleRestClient.getAllVehicles();
        } catch (Exception e) {
            log.error("Error retrieving all vehicles: {}", e.getMessage());
            throw new BadRequestException("Error retrieving all vehicles.");
        }
    }
}
