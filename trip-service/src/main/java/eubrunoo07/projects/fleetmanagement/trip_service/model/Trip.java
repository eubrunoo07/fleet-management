package eubrunoo07.projects.fleetmanagement.trip_service.model;

import eubrunoo07.projects.fleetmanagement.trip_service.enums.TripStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "trip")
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "driver_id", nullable = false)
    private Long driverId;
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;
    @Column(name = "origin", nullable = false)
    private String origin;
    @Column(name = "destination", nullable = false)
    private String destination;
    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "end_datetime")
    private LocalDateTime endDateTime;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus status;

}
