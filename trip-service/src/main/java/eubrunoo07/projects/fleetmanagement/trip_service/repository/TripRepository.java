package eubrunoo07.projects.fleetmanagement.trip_service.repository;

import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
