package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.MaintenanceType;
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
public class CreateMaintenanceRequest {

    private String vehicleChassisNumber;

    private MaintenanceType maintenanceType;

    private String requesterDocument;

    private RequesterType requesterType;

    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

    private CreateMaintenanceVehicleRequest vehicle;
}
