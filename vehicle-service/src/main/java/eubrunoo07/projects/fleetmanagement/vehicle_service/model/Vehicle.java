package eubrunoo07.projects.fleetmanagement.vehicle_service.model;

import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data

/**
 * CREATE TABLE vehicle (
 *     id SERIAL PRIMARY KEY,
 *     license_plate VARCHAR(10) NOT NULL UNIQUE,
 *     model VARCHAR(100) NOT NULL,
 *     model_year INTEGER NOT NULL CHECK (model_year > 2000),
 *     status VARCHAR(20) CHECK (status IN ('A', 'B', 'C', 'D', 'E'))
 * );
 */

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "model_year", nullable = false)
    private Integer modelYear;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "brand")
    private String brand;

    @PrePersist
    private void prePersist(){
        this.active = true;
    }


}
