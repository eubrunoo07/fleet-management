package eubrunoo07.projects.fleetmanagement.trip_service.mapper;

import eubrunoo07.projects.fleetmanagement.trip_service.client.DriverClient;
import eubrunoo07.projects.fleetmanagement.trip_service.client.VehicleClient;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.DriverInfoDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripRequestDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripResponseDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.VehicleInfoDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.enums.TripStatus;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.TripCanceledPublisher;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.representation.CanceledTripRepresentation;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.representation.TripFinishedRepresentation;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.representation.TripStartedRepresentation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TripMapper {

    @Autowired
    private DriverClient driverClient;
    @Autowired
    private VehicleClient vehicleClient;

    public TripResponseDTO mapToResponse(Trip trip){

        var driver = driverClient.getDriverById(trip.getDriverId()).getBody();
        var vehicle = vehicleClient.getVehicle(trip.getVehicleId()).getBody();

        DriverInfoDTO driverInfo = DriverInfoDTO
                .builder()
                .id(driver.getId())
                .fullName(driver.getFullName())
                .cpf(driver.getCpf())
                .build();

        VehicleInfoDTO vehicleInfo = VehicleInfoDTO
                .builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .modelYear(vehicle.getModelYear())
                .licensePlate(vehicle.getLicensePlate())
                .build();

        return TripResponseDTO
                .builder()
                .id(trip.getId())
                .origin(trip.getOrigin())
                .destination(trip.getDestination())
                .startDateTime(trip.getStartDateTime())
                .endDateTime(trip.getEndDateTime())
                .driver(driverInfo)
                .vehicle(vehicleInfo)
                .status(String.valueOf(trip.getStatus()))
                .build();
    }

    public Trip mapToEntity(TripRequestDTO dto){
        Trip trip = new Trip();
        BeanUtils.copyProperties(dto, trip);
        trip.setStatus(TripStatus.IN_PROGRESS);
        trip.setStartDateTime(LocalDateTime.now());
        trip.setEndDateTime(null);
        return trip;
    }

    public TripStartedRepresentation mapToRepresentationStarted(Trip trip) {
        var tripStarted = new TripStartedRepresentation();
        tripStarted.setTripId(trip.getId());
        tripStarted.setStatus(String.valueOf(trip.getStatus()));
        tripStarted.setDriverId(trip.getDriverId());
        tripStarted.setVehicleId(trip.getVehicleId());
        tripStarted.setStartDateTime(trip.getStartDateTime());
        return tripStarted;
    }

    public TripFinishedRepresentation mapToRepresentationFinished(Trip trip) {
        var tripFinished = new TripFinishedRepresentation();
        tripFinished.setDriverId(trip.getDriverId());
        tripFinished.setStatus(String.valueOf(trip.getStatus()));
        tripFinished.setVehicleId(trip.getVehicleId());
        return tripFinished;
    }

    public CanceledTripRepresentation mapToRepresentationCanceled(Trip trip) {
        var canceledTrip = new CanceledTripRepresentation();
        canceledTrip.setDriverId(trip.getDriverId());
        canceledTrip.setVehicleId(trip.getVehicleId());
        return canceledTrip;
    }
}
