package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.dto.request.CreateInteractionRequest;
import br.com.drivenation.motors.dto.request.CustomerEventRequest;
import br.com.drivenation.motors.enumeration.InteractionType;
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
@MongoEntity(collection = "interactions")
public class InteractionEntity {

    private ObjectId id;

    private String customerFirstName;

    private String customerLastName;

    private String customerDocument;

    private String customerEmail;

    private String customerPhoneNumber;

    private InteractionType type;

    private Double price;

    private Date date;

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;

    public static InteractionEntity valueOf(CreateInteractionRequest createInteractionRequest) {
        return InteractionEntity.builder()
                .customerFirstName(createInteractionRequest.getCustomerFirstName())
                .customerLastName(createInteractionRequest.getCustomerLastName())
                .customerDocument(createInteractionRequest.getCustomerDocument())
                .customerEmail(createInteractionRequest.getCustomerEmail())
                .customerPhoneNumber(createInteractionRequest.getCustomerPhoneNumber())
                .type(createInteractionRequest.getType())
                .price(createInteractionRequest.getPrice())
                .vehicleModel(createInteractionRequest.getVehicleModel())
                .vehicleManufacturer(createInteractionRequest.getVehicleManufacturer())
                .vehicleChassisNumber(createInteractionRequest.getVehicleChassisNumber())
                .date(new Date())
                .build();
    }

    public static InteractionEntity valueOf(CustomerEventRequest customerEventRequest) {
        return InteractionEntity.builder()
                .customerFirstName(customerEventRequest.getCustomerFirstName())
                .customerLastName(customerEventRequest.getCustomerLastName())
                .customerDocument(customerEventRequest.getCustomerDocument())
                .customerEmail(customerEventRequest.getCustomerEmail())
                .customerPhoneNumber(customerEventRequest.getCustomerPhoneNumber())
                .type(InteractionType.MAINTENANCE)
                .price(customerEventRequest.getPrice())
                .vehicleModel(customerEventRequest.getVehicleModel())
                .vehicleManufacturer(customerEventRequest.getVehicleManufacturer())
                .vehicleChassisNumber(customerEventRequest.getVehicleChassisNumber())
                .date(new Date())
                .build();
    }
}
