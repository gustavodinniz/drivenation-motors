package br.com.drivenation.motors.controller;

import br.com.drivenation.motors.dto.request.CreateInteractionRequest;
import br.com.drivenation.motors.service.InteractionService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/interactions")
public class InteractionController {

    @Inject
    InteractionService interactionService;

    @POST
    @ResponseStatus(201)
    public void createInteraction(@Valid CreateInteractionRequest createInteractionRequest) {
        interactionService.createInteraction(createInteractionRequest);
    }
}
