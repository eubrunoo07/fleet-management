package eubrunoo07.projects.fleetmanagement.vehicle_service.validator;

import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.VehicleValidationException;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import eubrunoo07.projects.fleetmanagement.vehicle_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleValidator {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void validateVehicleRequest(Vehicle vehicle){
        if(vehicle.getModelYear() <= 2000){
            throw new VehicleValidationException("Vehicle model year must be greater than 2000");
        }
        if(vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())){
            throw new VehicleValidationException("Vehicle license plate is already registered");
        }
    }

}
