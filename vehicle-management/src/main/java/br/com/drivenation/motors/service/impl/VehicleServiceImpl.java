package br.com.drivenation.motors.service.impl;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.request.UpdateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByChassisNumberResponse;
import br.com.drivenation.motors.dto.response.GetVehicleByIdResponse;
import br.com.drivenation.motors.dto.response.UpdateVehicleResponse;
import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.exception.ConflictException;
import br.com.drivenation.motors.repository.VehicleRepository;
import br.com.drivenation.motors.service.VehicleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class VehicleServiceImpl implements VehicleService {

    @Inject
    VehicleRepository vehicleRepository;


    @Override
    public void createVehicle(CreateVehicleRequest createVehicleRequest) {
        log.info("Creating vehicle with chassis number: {}", createVehicleRequest.getChassisNumber());
        vehicleRepository.findByChassisNumber(createVehicleRequest.getChassisNumber()).ifPresent(vehicle -> {
            log.error("A vehicle with chassis number {} already exists.", createVehicleRequest.getChassisNumber());
            throw new ConflictException("A vehicle with chassis number already exists.");
        });
        vehicleRepository.persist(VehicleEntity.valueOf(createVehicleRequest));
        log.info("Vehicle created.");
    }

    @Override
    public List<GetAllVehicleResponse> getAllVehicles() {
        log.info("Getting all vehicles");
        List<VehicleEntity> vehicles = vehicleRepository.findAll().list();
        log.info("Vehicles found: {}", vehicles.size());
        return vehicles.stream().map(GetAllVehicleResponse::valueOf).toList();
    }

    @Override
    public void deleteVehicle(ObjectId id) {
        log.info("Deleting vehicle with id: {}", id);
        vehicleRepository.deleteById(id);
        log.info("Vehicle deleted.");
    }

    @Override
    public UpdateVehicleResponse updateVehicle(ObjectId id, UpdateVehicleRequest updateVehicleRequest) {
        VehicleEntity vehicleEntity = findVehicleById(id);
        updateVehicle(updateVehicleRequest, vehicleEntity);

        vehicleRepository.update(vehicleEntity);
        log.info("Vehicle updated.");

        return UpdateVehicleResponse.valueOf(vehicleEntity);
    }

    @Override
    public GetVehicleByIdResponse getVehicleById(ObjectId id) {
        VehicleEntity vehicleEntity = findVehicleById(id);
        return GetVehicleByIdResponse.valueOf(vehicleEntity);
    }

    @Override
    public GetVehicleByChassisNumberResponse getVehicleByChassisNumber(String chassisNumber) {
        log.info("Finding vehicle with chassis number: {}", chassisNumber);
        VehicleEntity vehicleEntity = vehicleRepository.findByChassisNumber(chassisNumber).orElseThrow(() -> {
            log.error("Vehicle not found!");
            return new NotFoundException();
        });

        log.info("Vehicle found.");
        return GetVehicleByChassisNumberResponse.valueOf(vehicleEntity);
    }

    private VehicleEntity findVehicleById(ObjectId id) {
        log.info("Finding vehicle with id: {}", id);
        VehicleEntity vehicleEntity = Optional.ofNullable(vehicleRepository.findById(id)).orElseThrow(() -> {
            log.error("Vehicle not found!");
            return new NotFoundException();
        });
        log.info("Vehicle found.");
        return vehicleEntity;
    }

    private void updateVehicle(UpdateVehicleRequest updateVehicleRequest, VehicleEntity vehicleEntity) {
        log.info("Updating vehicle...");
        vehicleEntity.setModel(Optional.ofNullable(updateVehicleRequest.getModel()).orElse(vehicleEntity.getModel()));
        vehicleEntity.setYear(Optional.ofNullable(updateVehicleRequest.getYear()).orElse(vehicleEntity.getYear()));
        vehicleEntity.setColor(Optional.ofNullable(updateVehicleRequest.getColor()).orElse(vehicleEntity.getColor()));
        vehicleEntity.setManufacturer(Optional.ofNullable(updateVehicleRequest.getManufacturer()).orElse(vehicleEntity.getManufacturer()));
        vehicleEntity.setChassisNumber(Optional.ofNullable(updateVehicleRequest.getChassisNumber()).orElse(vehicleEntity.getChassisNumber()));
        vehicleEntity.setPrice(Optional.ofNullable(updateVehicleRequest.getPrice()).orElse(vehicleEntity.getPrice()));
        vehicleEntity.setStatus(Optional.ofNullable(updateVehicleRequest.getStatus()).orElse(vehicleEntity.getStatus()));
    }
}
