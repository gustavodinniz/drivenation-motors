package br.com.drivenation.motors.service;

import br.com.drivenation.motors.dto.request.CreateDealRequest;
import br.com.drivenation.motors.dto.request.CreateMaintenanceRequest;

public interface DealService {

    void createDeal(CreateDealRequest createDealRequest);

    void createMaintenance(CreateMaintenanceRequest createMaintenanceRequest);
}
