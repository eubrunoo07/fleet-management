package eubrunoo07.projects.fleetmanagement.vehicle_service.dto;

import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {

    private Long id;
    private String brand;
    private String model;
    private Integer modelYear;
    private String licensePlate;
    private String status;
    private boolean active;

}
