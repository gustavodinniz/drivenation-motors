package br.com.drivenation.motors.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerAddressRequest {

    private String street;

    private String number;

    private String complement;

    private String district;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
