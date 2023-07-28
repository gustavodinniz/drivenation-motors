package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.dto.request.CreateCustomerAddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressEntity {

    private String street;

    private String number;

    private String complement;

    private String district;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    public static CustomerAddressEntity valueOf(CreateCustomerAddressRequest address) {
        return CustomerAddressEntity.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .district(address.getDistrict())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build();
    }
}
