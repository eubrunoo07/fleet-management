package eubrunoo07.projects.fleetmanagement.vehicle_service.service;

import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleRequestDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleResponseDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);

    List<VehicleResponseDTO> getVehicles();

    VehicleResponseDTO getVehicleById(Long id);

    Vehicle updateVehicle(Long id, @Valid VehicleRequestDTO dto);

    void deleteVehicle(Long id);
}
