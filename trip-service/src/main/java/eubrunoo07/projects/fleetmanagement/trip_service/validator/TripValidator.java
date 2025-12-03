package eubrunoo07.projects.fleetmanagement.trip_service.validator;

import eubrunoo07.projects.fleetmanagement.trip_service.client.DriverClient;
import eubrunoo07.projects.fleetmanagement.trip_service.client.VehicleClient;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripValidationException;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
public class TripValidator {

    @Autowired
    private VehicleClient vehicleClient;
    @Autowired
    private DriverClient driverClient;

    public void validateTripRequest(Trip trip){

        var driver = driverClient.getDriverById(trip.getDriverId()).getBody();
        var vehicle = vehicleClient.getVehicle(trip.getVehicleId()).getBody();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDate cnhExpiresDate = YearMonth.parse(driver.getCnhExpiresDate(), formatter).atDay(1);

        if(LocalDate.now().isAfter(cnhExpiresDate)){
            throw new TripValidationException("The driver's license has expired");
        }
        if(!driver.getActive()){
            throw new TripValidationException("The driver is not active");
        }
        if(!vehicle.getActive()){
            throw new TripValidationException("The vehicle is not active");
        }
        if(!vehicle.getStatus().equals("AVAILABLE")){
            throw new TripValidationException("The vehicle is not available to trip");
        }
    }

}
