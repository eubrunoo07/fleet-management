package eubrunoo07.projects.fleetmanagement.driver_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponseDTO {

    private Long id;
    private String fullName;
    private String cpf;
    private String cnhNumber;
    private String cnhCategory;
    @JsonFormat(pattern = "MM-yyyy", shape = JsonFormat.Shape.STRING)
    private YearMonth cnhExpiresDate;
    private boolean active;

}
