package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateInteractionRequest;
import br.com.drivenation.motors.entity.InteractionEntity;
import br.com.drivenation.motors.repository.InteractionRepository;
import br.com.drivenation.motors.service.InteractionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class InteractionServiceImpl implements InteractionService {

    @Inject
    InteractionRepository interactionRepository;

    @Override
    public void createInteraction(CreateInteractionRequest createInteractionRequest) {
        log.info("Creating interaction for customer with document: {}", createInteractionRequest.getCustomerDocument());
        interactionRepository.persist(InteractionEntity.valueOf(createInteractionRequest));
        log.info("Interaction created.");
    }
}
