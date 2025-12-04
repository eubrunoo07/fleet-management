package eubrunoo07.projects.fleetmanagement.trip_service.publisher.representation;

import lombok.Data;

@Data
public class CanceledTripRepresentation {
    private Long driverId;
    private Long vehicleId;
}
