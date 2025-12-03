package eubrunoo07.projects.fleetmanagement.trip_service.client.representation;

import lombok.Data;

@Data
public class DriverRepresentation {
    private Long id;
    private String fullName;
    private String cpf;
    private String cnhNumber;
    private String cnhCategory;
    private String cnhExpiresDate;
    private Boolean active;
}
