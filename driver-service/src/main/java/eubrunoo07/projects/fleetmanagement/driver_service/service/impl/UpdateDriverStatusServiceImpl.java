package eubrunoo07.projects.fleetmanagement.driver_service.service.impl;

import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;
import eubrunoo07.projects.fleetmanagement.driver_service.exception.DriverNotFoundException;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import eubrunoo07.projects.fleetmanagement.driver_service.repository.DriverRepository;
import eubrunoo07.projects.fleetmanagement.driver_service.service.UpdateDriverStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDriverStatusServiceImpl implements UpdateDriverStatusService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public void updateStatus(Long id, DriverStatus status) {
        Driver driver = driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException("Driver not found with id: " + id));
        driver.setStatus(status);
        driverRepository.save(driver);
    }
}
