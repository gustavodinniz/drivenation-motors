package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateDealRequest;
import br.com.drivenation.motors.entity.DealEntity;
import br.com.drivenation.motors.repository.DealRepository;
import br.com.drivenation.motors.service.DealService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DealServiceImpl implements DealService {

    @Inject
    DealRepository dealRepository;

    @Override
    public void createDeal(CreateDealRequest createDealRequest) {
        log.info("Creating deal for vehicle with chassis number: {}", createDealRequest.getVehicleChassisNumber());
        dealRepository.persist(DealEntity.valueOf(createDealRequest));
        log.info("Deal created.");
    }
}
