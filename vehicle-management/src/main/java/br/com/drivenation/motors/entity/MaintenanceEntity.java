package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;
import br.com.drivenation.motors.enumeration.MaintenanceStatus;
import br.com.drivenation.motors.enumeration.RequesterType;
import br.com.drivenation.motors.enumeration.MaintenanceType;
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
@MongoEntity(collection = "maintenance")
public class MaintenanceEntity {

    private ObjectId id;

    private String vehicleModel;

    private String vehicleManufacturer;

    private String vehicleChassisNumber;

    private MaintenanceType maintenanceType;

    private RequesterType requesterType;

    private String requesterName;

    private String requesterDocument;

    private Double price;

    private MaintenanceStatus status;

    private Date entryDate;

    private Date exitDate;


    public static MaintenanceEntity valueOf(CreateMaintenanceRequest createMaintenanceRequest) {
        return MaintenanceEntity.builder()
                .vehicleModel(createMaintenanceRequest.getVehicleModel())
                .vehicleManufacturer(createMaintenanceRequest.getVehicleManufacturer())
                .vehicleChassisNumber(createMaintenanceRequest.getVehicleChassisNumber())
                .maintenanceType(createMaintenanceRequest.getMaintenanceType())
                .requesterType(createMaintenanceRequest.getRequesterType())
                .requesterName(createMaintenanceRequest.getRequesterName())
                .requesterDocument(createMaintenanceRequest.getRequesterDocument())
                .price(createMaintenanceRequest.getPrice())
                .entryDate(new Date())
                .status(MaintenanceStatus.IN_PROGRESS)
                .build();
    }
}
