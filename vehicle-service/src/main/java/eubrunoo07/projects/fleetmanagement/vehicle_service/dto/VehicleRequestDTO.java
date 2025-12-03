package eubrunoo07.projects.fleetmanagement.vehicle_service.dto;

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
public class VehicleRequestDTO {

    @NotBlank(message = "Vehicle brand is mandatory")
    private String brand;
    @NotBlank(message = "Vehicle model is mandatory")
    private String model;
    @NotNull(message = "Vehicle model year is mandatory")
    private Integer modelYear;
    @NotBlank(message = "Vehicle license plate is mandatory")
    private String licensePlate;

}
