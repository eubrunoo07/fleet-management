package eubrunoo07.projects.fleetmanagement.trip_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverInfoDTO {

    private Long id;
    private String fullName;
    private String cpf;

}
