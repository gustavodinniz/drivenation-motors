package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.DealType;
import br.com.drivenation.motors.enumeration.PaymentStatus;
import br.com.drivenation.motors.enumeration.PaymentType;
import br.com.drivenation.motors.enumeration.RequesterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDealRequest {

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;

    private DealType type;

    private RequesterType requesterType;

    private String requesterName;

    private String requesterDocument;

    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

    private Double price;
}
