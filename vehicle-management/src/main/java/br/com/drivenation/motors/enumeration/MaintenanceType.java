package br.com.drivenation.motors.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaintenanceType {

    OIL_CHANGE(100.0),

    ENGINE_REPAIR(500.0),

    BRAKE_CHECK(150.0),

    TIRE_ROTATION(150.0),

    BATTERY_REPLACEMENT(200.0),

    BODY_REPAIR(400.0);

    private final Double price;

}
