package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateVehicleRequest;
import br.com.drivenation.motors.dto.response.GetAllVehicleResponse;
import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.repository.VehicleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import java.util.List;

@Slf4j
@ApplicationScoped
public class VehicleServiceImpl implements VehicleService {

    @Inject
    VehicleRepository vehicleRepository;


    @Override
    public void createVehicle(CreateVehicleRequest createVehicleRequest) {
        log.info("Creating vehicle: {} {}", createVehicleRequest.getManufacturer(), createVehicleRequest.getModel());
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
}
