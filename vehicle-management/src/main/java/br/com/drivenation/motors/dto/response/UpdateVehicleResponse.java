package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
@AllArgsConstructor
public class UpdateVehicleResponse {

    private ObjectId id;

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;

    public static UpdateVehicleResponse valueOf(VehicleEntity vehicleEntity) {
        return UpdateVehicleResponse.builder()
                .id(vehicleEntity.getId())
                .model(vehicleEntity.getModel())
                .year(vehicleEntity.getYear())
                .color(vehicleEntity.getColor())
                .manufacturer(vehicleEntity.getManufacturer())
                .chassisNumber(vehicleEntity.getChassisNumber())
                .price(vehicleEntity.getPrice())
                .status(vehicleEntity.getStatus())
                .build();
    }
}
