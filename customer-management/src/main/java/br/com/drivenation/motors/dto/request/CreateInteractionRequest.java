package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.InteractionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateInteractionRequest {

    @NotBlank(message = "{400.000}")
    private String customerFirstName;

    @NotBlank(message = "{400.000}")
    private String customerLastName;

    @NotBlank(message = "{400.000}")
    private String customerDocument;

    @NotNull(message = "{400.000}")
    private InteractionType type;

    @NotNull(message = "{400.000}")
    private Double price;

    @NotBlank(message = "{400.000}")
    private String vehicleModel;

    @NotBlank(message = "{400.000}")
    private String vehicleManufacturer;

    @NotBlank(message = "{400.000}")
    private String vehicleChassisNumber;
}
