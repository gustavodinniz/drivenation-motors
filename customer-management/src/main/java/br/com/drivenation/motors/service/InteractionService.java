package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateInteractionRequest;
import br.com.drivenation.motors.dto.request.CustomerEventRequest;

public interface InteractionService {

    void createInteraction(CreateInteractionRequest createInteractionRequest);

    void receiveInteraction(CustomerEventRequest customerEventRequest);
}
