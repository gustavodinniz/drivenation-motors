package br.com.drivenation.motors.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceVehicleRequest {

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

}
