package br.com.drivenation.motors.dto.response;

import br.com.drivenation.motors.entity.CustomerEntity;
import br.com.drivenation.motors.enumeration.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetCustomerByDocumentResponse {

    private String firstName;

    private String document;

    private String email;

    private String phoneNumber;

    private CustomerStatus status;

    public static GetCustomerByDocumentResponse valueOf(CustomerEntity customerEntity) {
        return GetCustomerByDocumentResponse.builder()
                .firstName(customerEntity.getFirstName())
                .document(customerEntity.getDocument())
                .email(customerEntity.getEmail())
                .phoneNumber(customerEntity.getPhoneNumber())
                .status(customerEntity.getStatus())
                .build();
    }
}
