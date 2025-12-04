package eubrunoo07.projects.fleetmanagement.vehicle_service.subscriber.representation;

import lombok.Data;

@Data
public class CanceledTripRepresentation {
    private Long driverId;
    private Long vehicleId;
}
