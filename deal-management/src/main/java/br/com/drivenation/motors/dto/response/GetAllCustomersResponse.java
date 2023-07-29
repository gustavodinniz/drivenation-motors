package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.enumeration.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetAllCustomersResponse {

    private String firstName;

    private String lastName;

    private String document;

    private String email;

    private String phoneNumber;

    private CustomerStatus status;

}
