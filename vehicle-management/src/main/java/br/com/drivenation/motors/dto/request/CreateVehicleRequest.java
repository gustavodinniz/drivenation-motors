package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.OwnershipStatus;
import br.com.drivenation.motors.enumeration.VehicleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateVehicleRequest {

    @NotBlank(message = "{400.000}")
    private String model;

    @NotNull(message = "{400.000}")
    private Integer year;

    @NotBlank(message = "{400.000}")
    private String color;

    @NotBlank(message = "{400.000}")
    private String manufacturer;

    @NotBlank(message = "{400.000}")
    private String chassisNumber;

    @NotNull(message = "{400.000}")
    private Double price;

    @NotNull(message = "{400.000}")
    private VehicleStatus status;

    @NotNull(message = "{400.000}")
    private OwnershipStatus ownership;

}
