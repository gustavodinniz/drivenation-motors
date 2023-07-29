package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enumeration.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetVehicleByChassisNumberResponse {


    private String model;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;

    public static GetVehicleByChassisNumberResponse valueOf(VehicleEntity vehicleEntity) {
        return GetVehicleByChassisNumberResponse.builder()
                .model(vehicleEntity.getModel())
                .manufacturer(vehicleEntity.getManufacturer())
                .chassisNumber(vehicleEntity.getChassisNumber())
                .price(vehicleEntity.getPrice())
                .status(vehicleEntity.getStatus())
                .build();
    }
}
