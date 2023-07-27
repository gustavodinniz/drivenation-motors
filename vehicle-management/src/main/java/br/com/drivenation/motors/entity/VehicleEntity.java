package br.com.drivenation.motors.entity;

import br.com.drivenation.motors.enums.VehicleStatus;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "vehicles")
public class VehicleEntity {

    private ObjectId id;

    private String model;

    private Integer year;

    private String color;

    private String manufacturer;

    private String chassisNumber;

    private Double price;

    private VehicleStatus status;
}
