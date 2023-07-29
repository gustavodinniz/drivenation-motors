package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enumeration.OwnershipStatus;
import br.com.drivenation.motors.enumeration.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
public class GetAllVehicleResponse {

    private ObjectId id;

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;

    private OwnershipStatus ownership;


    public static GetAllVehicleResponse valueOf(VehicleEntity vehicle) {
        return GetAllVehicleResponse.builder()
                .id(vehicle.getId())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .color(vehicle.getColor())
                .manufacturer(vehicle.getManufacturer())
                .chassisNumber(vehicle.getChassisNumber())
                .price(vehicle.getPrice())
                .status(vehicle.getStatus())
                .ownership(vehicle.getOwnership())
                .build();
    }
}
