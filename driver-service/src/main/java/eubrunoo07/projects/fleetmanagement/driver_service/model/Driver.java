package eubrunoo07.projects.fleetmanagement.driver_service.model;

import eubrunoo07.projects.fleetmanagement.driver_service.enums.CnhCategory;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Table(name = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(name = "cnh_number", nullable = false, length = 9)
    private String cnhNumber;
    @Column(name = "cnh_category", length = 20)
    @Enumerated(EnumType.STRING)
    private CnhCategory cnhCategory;
    @Column(name = "cnh_expires_date")
    private YearMonth cnhExpiresDate;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "driver_status")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @PrePersist
    private void prePersist(){
        this.active = true;
        this.status = DriverStatus.AVAILABLE;
    }

}
