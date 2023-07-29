package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateCustomerRequest {

    private String firstName;

    private String lastName;

    private String document;

    private String email;

    private String phoneNumber;

    private String dateOfBirth;

    private CreateCustomerAddressRequest address;

    private CustomerStatus status;
}
