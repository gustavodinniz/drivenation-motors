package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.client.CustomerRestClient;
import br.com.drivenation.motors.client.VehicleRestClient;
import br.com.drivenation.motors.dto.request.CreateDealRequest;
import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.dto.request.CustomerEventRequest;
import br.com.drivenation.motors.dto.request.VehicleEventRequest;
import br.com.drivenation.motors.dto.response.GetCustomerByDocumentResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByChassisNumberResponse;
import br.com.drivenation.motors.entity.DealEntity;
import br.com.drivenation.motors.enumeration.*;
import br.com.drivenation.motors.exception.BadRequestException;
import br.com.drivenation.motors.message.KafkaEvent;
import br.com.drivenation.motors.repository.DealRepository;
import br.com.drivenation.motors.service.DealService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Date;

@Slf4j
@ApplicationScoped
public class DealServiceImpl implements DealService {

    @Inject
    DealRepository dealRepository;

    @Inject
    KafkaEvent kafkaEvent;

    @Inject
    @RestClient
    CustomerRestClient customerRestClient;

    @Inject
    @RestClient
    VehicleRestClient vehicleRestClient;

    @Override
    public void createDeal(CreateDealRequest createDealRequest) {
        log.info("Creating deal for vehicle with chassis number: {}", createDealRequest.getVehicleChassisNumber());
        dealRepository.persist(DealEntity.valueOf(createDealRequest));
        log.info("Deal created.");
    }

    @Override
    public void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        log.info("Creating maintenance for vehicle with chassis number: {}", createMaintenanceRequest.getVehicleChassisNumber());
        if (RequesterType.DEALERSHIP.equals(createMaintenanceRequest.getRequesterType())) {
            handleDealershipMaintenance(createMaintenanceRequest);
        } else {
            handleCustomerMaintenance(createMaintenanceRequest);
        }

    }

    private void handleCustomerMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        log.info("Maintenance type: {}, Requester: {}", createMaintenanceRequest.getMaintenanceType(), createMaintenanceRequest.getRequesterType());
        var customer = callCustomerRestClientGetCustomerByDocument(createMaintenanceRequest.getRequesterDocument());

        if (!CustomerStatus.ACTIVE.equals(customer.getStatus())) {
            log.error("Customer with document: {} is not active, status: {}", createMaintenanceRequest.getRequesterDocument(), customer.getStatus());
            throw new BadRequestException("Customer is not active.");
        }

        DealEntity dealEntity = buildCustomerMaintenanceDeal(createMaintenanceRequest, customer);
        log.info("Saving deal type: {} for vehicle with chassis number: {}", DealType.MAINTENANCE, createMaintenanceRequest.getVehicleChassisNumber());
        dealRepository.persist(dealEntity);
        log.info("Deal saved.");

        kafkaEvent.sendCustomerKafkaEvent(buildCustomerMaintenanceEvent(createMaintenanceRequest, customer));
    }

    private static CustomerEventRequest buildCustomerMaintenanceEvent(CreateMaintenanceRequest createMaintenanceRequest, GetCustomerByDocumentResponse customer) {
        return CustomerEventRequest.builder()
                .customerFirstName(customer.getFirstName())
                .customerLastName(customer.getLastName())
                .customerDocument(customer.getDocument())
                .customerEmail(customer.getEmail())
                .customerPhoneNumber(customer.getPhoneNumber())
                .price(createMaintenanceRequest.getMaintenanceType().getPrice())
                .vehicleModel(createMaintenanceRequest.getVehicle().getModel())
                .vehicleManufacturer(createMaintenanceRequest.getVehicle().getManufacturer())
                .vehicleChassisNumber(createMaintenanceRequest.getVehicleChassisNumber())
                .build();
    }

    private void handleDealershipMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        log.info("Maintenance type: {}, Requester: {}", createMaintenanceRequest.getMaintenanceType(), createMaintenanceRequest.getRequesterType());
        var vehicle = callVehicleRestClientGetVehicleByChassisNumber(createMaintenanceRequest.getVehicleChassisNumber());

        if (!vehicle.getStatus().equals(VehicleStatus.FOR_SALE)) {
            log.error("Vehicle with chassis number: {} not available, status: {}", createMaintenanceRequest.getVehicleChassisNumber(), vehicle.getStatus());
            throw new BadRequestException("Vehicle not available.");
        }

        DealEntity dealEntity = buildDealershipMaintenanceDeal(createMaintenanceRequest, vehicle);
        log.info("Saving deal type: {} for vehicle with chassis number: {}", DealType.MAINTENANCE, createMaintenanceRequest.getVehicleChassisNumber());
        dealRepository.persist(dealEntity);
        log.info("Deal saved.");

        kafkaEvent.sendVehicleKafkaEvent(buildDealershipMaintenanceEvent(createMaintenanceRequest, vehicle, dealEntity));
    }

    private VehicleEventRequest buildDealershipMaintenanceEvent(CreateMaintenanceRequest createMaintenanceRequest, GetVehicleByChassisNumberResponse vehicle, DealEntity dealEntity) {
        return VehicleEventRequest.builder()
                .vehicleModel(vehicle.getModel())
                .vehicleManufacturer(vehicle.getManufacturer())
                .vehicleChassisNumber(vehicle.getChassisNumber())
                .maintenanceType(createMaintenanceRequest.getMaintenanceType())
                .requesterType(createMaintenanceRequest.getRequesterType())
                .requesterName(dealEntity.getRequesterName())
                .requesterDocument(dealEntity.getRequesterDocument())
                .build();
    }

    private DealEntity buildCustomerMaintenanceDeal(CreateMaintenanceRequest createMaintenanceRequest, GetCustomerByDocumentResponse customer) {
        return DealEntity.builder()
                .vehicleModel(createMaintenanceRequest.getVehicle().getModel())
                .vehicleManufacturer(createMaintenanceRequest.getVehicle().getManufacturer())
                .vehicleChassisNumber(createMaintenanceRequest.getVehicleChassisNumber())
                .type(DealType.MAINTENANCE)
                .requesterType(createMaintenanceRequest.getRequesterType())
                .requesterName(customer.getFirstName().concat(" ").concat(customer.getLastName()))
                .requesterDocument(customer.getDocument())
                .price(createMaintenanceRequest.getMaintenanceType().getPrice())
                .paymentType(createMaintenanceRequest.getPaymentType())
                .paymentStatus(createMaintenanceRequest.getPaymentStatus())
                .createdAt(new Date())
                .build();
    }

    private DealEntity buildDealershipMaintenanceDeal(CreateMaintenanceRequest createMaintenanceRequest, GetVehicleByChassisNumberResponse vehicle) {
        return DealEntity.builder()
                .vehicleModel(vehicle.getModel())
                .vehicleManufacturer(vehicle.getManufacturer())
                .vehicleChassisNumber(vehicle.getChassisNumber())
                .type(DealType.MAINTENANCE)
                .requesterType(createMaintenanceRequest.getRequesterType())
                .requesterName("DriveNation Motors")
                .requesterDocument("48051879000160")
                .price(createMaintenanceRequest.getMaintenanceType().getPrice())
                .paymentType(PaymentType.DEALERSHIP_PAYMENT)
                .paymentStatus(PaymentStatus.COMPLETED)
                .createdAt(new Date())
                .build();
    }

    private GetVehicleByChassisNumberResponse callVehicleRestClientGetVehicleByChassisNumber(String chassisNumber) {
        try {
            log.info("Getting vehicle by chassis number: {}", chassisNumber);
            return vehicleRestClient.getVehicleByChassisNumber(chassisNumber);
        } catch (Exception e) {
            log.error("Error retrieving vehicle data: {}", e.getMessage());
            throw new BadRequestException("Error retrieving vehicle data.");
        }
    }


    private GetCustomerByDocumentResponse callCustomerRestClientGetCustomerByDocument(String document) {
        try {
            log.info("Getting customer by document: {}", document);
            return customerRestClient.getCustomerByDocument(document);
        } catch (Exception e) {
            log.error("Error retrieving customer data: {}", e.getMessage());
            throw new BadRequestException("Error retrieving customer data.");
        }
    }


}
