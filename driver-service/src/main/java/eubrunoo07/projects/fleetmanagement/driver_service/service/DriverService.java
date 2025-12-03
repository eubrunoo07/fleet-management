package eubrunoo07.projects.fleetmanagement.driver_service.service;

import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverRequestDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverResponseDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface DriverService {
    Driver createDriver(Driver driver);

    List<DriverResponseDTO> getDrivers();

    DriverResponseDTO getDriverById(Long id);

    Driver updateDriver(DriverRequestDTO dto, Long id);

    void deleteDriver(Long id);
}
