package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetAllVehicleResponse {

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;


    public static GetAllVehicleResponse valueOf(VehicleEntity vehicle) {
        return GetAllVehicleResponse.builder()
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .color(vehicle.getColor())
                .manufacturer(vehicle.getManufacturer())
                .chassisNumber(vehicle.getChassisNumber())
                .price(vehicle.getPrice())
                .status(vehicle.getStatus())
                .build();
    }
}
