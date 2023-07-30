package br.com.drivenation.motors.message;

import br.com.drivenation.motors.dto.request.CustomerEventRequest;
import br.com.drivenation.motors.dto.request.VehicleEventRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class KafkaEvent {

    @Channel("vehicle")
    Emitter<VehicleEventRequest> vehicleRequestEmitter;

    @Channel("customer")
    Emitter<CustomerEventRequest> customerRequestEmitter;

    public void sendVehicleKafkaEvent(VehicleEventRequest vehicleEventRequest) {
        log.info("Sending kafka event: {}", vehicleEventRequest);
        vehicleRequestEmitter.send(vehicleEventRequest).toCompletableFuture().join();
        log.info("Kafka event sent.");
    }

    public void sendCustomerKafkaEvent(CustomerEventRequest customerEventRequest) {
        log.info("Sending kafka event: {}", customerEventRequest);
        customerRequestEmitter.send(customerEventRequest).toCompletableFuture().join();
        log.info("Kafka event sent.");
    }
}
