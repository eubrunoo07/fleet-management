package eubrunoo07.projects.fleetmanagement.driver_service.service;

import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;

public interface UpdateDriverStatusService {
    void updateStatus(Long id, DriverStatus status);
}
