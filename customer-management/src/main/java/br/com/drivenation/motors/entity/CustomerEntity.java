package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.dto.request.CreateCustomerRequest;
import br.com.drivenation.motors.enums.CustomerStatus;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "customers")
public class CustomerEntity {

    private ObjectId id;

    private String firstName;

    private String lastName;

    private String document;

    private String email;

    private String phoneNumber;

    private String dateOfBirth;

    private CustomerAddressEntity address;

    private Date registrationDate;

    private CustomerStatus status;

    public static CustomerEntity valueOf(CreateCustomerRequest createCustomerRequest) {
        return CustomerEntity.builder()
                .firstName(createCustomerRequest.getFirstName())
                .lastName(createCustomerRequest.getLastName())
                .document(createCustomerRequest.getDocument())
                .email(createCustomerRequest.getEmail())
                .phoneNumber(createCustomerRequest.getPhoneNumber())
                .dateOfBirth(createCustomerRequest.getDateOfBirth())
                .address(CustomerAddressEntity.valueOf(createCustomerRequest.getAddress()))
                .registrationDate(new Date())
                .status(createCustomerRequest.getStatus())
                .build();
    }
}
