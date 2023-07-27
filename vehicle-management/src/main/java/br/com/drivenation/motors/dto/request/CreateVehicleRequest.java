package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.entity.VehicleEntity;
import br.com.drivenation.motors.enums.VehicleStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CreateVehicleRequest {

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;

    @JsonIgnore
    public VehicleEntity toEntity() {
        return VehicleEntity.builder()
                .model(model)
                .year(year)
                .color(color)
                .manufacturer(manufacturer)
                .chassisNumber(chassisNumber)
                .price(price)
                .status(status)
                .build();
    }
}
