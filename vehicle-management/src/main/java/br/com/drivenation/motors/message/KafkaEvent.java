package br.com.drivenation.motors.message;

import br.com.drivenation.motors.dto.request.VehicleEventRequest;
import br.com.drivenation.motors.service.MaintenanceService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class KafkaEvent {


    @Inject
    MaintenanceService maintenanceService;

    @Incoming("vehicle")
    public void receiveMaintenance(VehicleEventRequest vehicleEventRequest) {
        log.info("Received maintenance for vehicle with chassis number: {}",
                vehicleEventRequest.getVehicleChassisNumber());
        maintenanceService.receiveMaintenance(vehicleEventRequest);
    }
}
