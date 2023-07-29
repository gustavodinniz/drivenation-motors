package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateVehicleRequest {

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;
}
