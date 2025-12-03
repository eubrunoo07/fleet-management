package eubrunoo07.projects.fleetmanagement.trip_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDTO {
    @NotNull(message = "Driver is mandatory to trip")
    private Long driverId;
    @NotNull(message = "Vehicle is mandatory to trip")
    private Long vehicleId;
    @NotBlank(message = "Origin is mandatory to trip")
    private String origin;
    @NotBlank(message = "Destination is mandatory to trip")
    private String destination;
}
