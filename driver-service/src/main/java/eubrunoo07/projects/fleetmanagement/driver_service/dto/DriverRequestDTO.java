package eubrunoo07.projects.fleetmanagement.driver_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequestDTO {

    @NotBlank(message = "Full name is mandatory")
    private String fullName;
    @NotBlank(message = "CPF is mandatory")
    @CPF(message = "Invalid CPF")
    private String cpf;
    @NotBlank(message = "CNH Number is mandatory")
    private String cnhNumber;
    @NotBlank(message = "CNH Category is mandatory")
    private String cnhCategory;
    @JsonFormat(pattern = "MM-yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "CNH expires date is mandatory")
    private YearMonth cnhExpiresDate;

}
