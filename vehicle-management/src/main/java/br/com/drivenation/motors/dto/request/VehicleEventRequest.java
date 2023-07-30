package br.com.drivenation.motors.dto.request;

import br.com.drivenation.motors.enumeration.MaintenanceType;
import br.com.drivenation.motors.enumeration.RequesterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@Jacksonized
@AllArgsConstructor
public class VehicleEventRequest {

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;

    private MaintenanceType maintenanceType;

    private RequesterType requesterType;

    private String requesterName;

    private String requesterDocument;

}
