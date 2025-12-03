package eubrunoo07.projects.fleetmanagement.vehicle_service.service.impl;

import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.VehicleNotFoundException;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import eubrunoo07.projects.fleetmanagement.vehicle_service.repository.VehicleRepository;
import eubrunoo07.projects.fleetmanagement.vehicle_service.service.UpdateVehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateVehicleStatusServiceImpl implements UpdateVehicleStatusService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public void updateStatus(Long id, VehicleStatus status) {
        Vehicle vehicle = vehicleRepository
                .findById(id).orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with id: " + id));
        vehicle.setStatus(status);
        vehicleRepository.save(vehicle);
    }
}
