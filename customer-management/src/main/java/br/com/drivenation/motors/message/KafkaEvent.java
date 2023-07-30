package br.com.drivenation.motors.message;

import br.com.drivenation.motors.dto.request.CustomerEventRequest;
import br.com.drivenation.motors.service.InteractionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class KafkaEvent {


    @Inject
    InteractionService interactionService;

    @Incoming("customer")
    public void receiveInteraction(CustomerEventRequest customerEventRequest) {
        log.info("Received interaction for customer: {}", customerEventRequest.getCustomerDocument());
        interactionService.receiveInteraction(customerEventRequest);
    }
}
