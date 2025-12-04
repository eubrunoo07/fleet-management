package eubrunoo07.projects.fleetmanagement.trip_service.service;

import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripRequestDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripResponseDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface TripService {
    Trip initTrip(Trip trip);

    List<TripResponseDTO> getAllTrips();

    TripResponseDTO getTripById(Long id);

    Trip updateTrip(Long id, @Valid TripRequestDTO dto);

    void finishTrip(Long id);
}
