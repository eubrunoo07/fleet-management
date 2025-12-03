package eubrunoo07.projects.fleetmanagement.driver_service.subscriber.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripStartedRepresentation {
    private Long tripId;
    private Long driverId;
    private Long vehicleId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDateTime;
    private String status;

}
