package eubrunoo07.projects.fleetmanagement.vehicle_service.exception;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
