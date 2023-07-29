package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.MaintenanceType;
import br.com.drivenation.motors.enumeration.RequesterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateMaintenanceRequest {

    @NotBlank(message = "{400.000}")
    private String vehicleModel;

    @NotBlank(message = "{400.000}")
    private String vehicleManufacturer;

    @NotBlank(message = "{400.000}")
    private String vehicleChassisNumber;

    @NotNull(message = "{400.000}")
    private MaintenanceType maintenanceType;

    @NotNull(message = "{400.000}")
    private RequesterType requesterType;

    @NotBlank(message = "{400.000}")
    private String requesterName;

    @NotBlank(message = "{400.000}")
    private String requesterDocument;

    @NotNull(message = "{400.000}")
    private Double price;
}
