package eubrunoo07.projects.fleetmanagement.vehicle_service.repository;

import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    boolean existsByLicensePlate(String licensePlate);
}
