package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.dto.request.CreateDealRequest;
import br.com.drivenation.motors.enumeration.DealType;
import br.com.drivenation.motors.enumeration.PaymentStatus;
import br.com.drivenation.motors.enumeration.PaymentType;
import br.com.drivenation.motors.enumeration.RequesterType;
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
@MongoEntity(collection = "deals")
public class DealEntity {

    private ObjectId id;

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;

    private DealType type;

    private RequesterType requesterType;

    private String requesterName;

    private String requesterDocument;

    private Double price;

    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

    private Date createdAt;

    public static DealEntity valueOf(CreateDealRequest createDealRequest) {
        return DealEntity.builder()
                .vehicleChassisNumber(createDealRequest.getVehicleChassisNumber())
                .type(createDealRequest.getType())
                .requesterType(createDealRequest.getRequesterType())
                .requesterDocument(createDealRequest.getRequesterDocument())
                .price(createDealRequest.getPrice())
                .paymentType(createDealRequest.getPaymentType())
                .paymentStatus(createDealRequest.getPaymentStatus())
                .createdAt(new Date())
                .build();
    }
}
