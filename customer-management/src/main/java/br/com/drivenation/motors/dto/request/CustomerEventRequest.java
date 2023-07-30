package br.com.drivenation.motors.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class CustomerEventRequest {


    private String customerFirstName;

    private String customerLastName;

    private String customerDocument;

    private String customerEmail;

    private String customerPhoneNumber;

    private Double price;

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;
}
