package eubrunoo07.projects.fleetmanagement.vehicle_service.service;

import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;

public interface UpdateVehicleStatusService {
    void updateStatus(Long id, VehicleStatus status);
}
