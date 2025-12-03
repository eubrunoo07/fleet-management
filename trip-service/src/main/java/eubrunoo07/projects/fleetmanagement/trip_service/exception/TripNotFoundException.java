package eubrunoo07.projects.fleetmanagement.trip_service.exception;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(String message) {
        super(message);
    }
}
