package eubrunoo07.projects.fleetmanagement.trip_service.client.representation;

import lombok.Data;

@Data
public class VehicleRepresentation {

    private Long id;
    private String licensePlate;
    private String model;
    private Integer modelYear;
    private String status;
    private Boolean active;
    private String brand;


}
