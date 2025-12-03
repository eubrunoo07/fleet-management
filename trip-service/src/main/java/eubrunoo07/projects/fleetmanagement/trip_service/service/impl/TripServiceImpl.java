package eubrunoo07.projects.fleetmanagement.trip_service.service.impl;

import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripRequestDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripResponseDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripNotFoundException;
import eubrunoo07.projects.fleetmanagement.trip_service.mapper.TripMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import eubrunoo07.projects.fleetmanagement.trip_service.repository.TripRepository;
import eubrunoo07.projects.fleetmanagement.trip_service.service.TripService;
import eubrunoo07.projects.fleetmanagement.trip_service.validator.TripValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripMapper mapper;
    @Autowired
    private TripValidator validator;

    @Override
    public Trip initTrip(Trip trip) {
        validator.validateTripRequest(trip);
        return tripRepository.save(trip);
    }

    @Override
    public List<TripResponseDTO> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        List<TripResponseDTO> response = new ArrayList<>();
        trips.forEach(trip -> response.add(mapper.mapToResponse(trip)));
        return response;
    }

    @Override
    public TripResponseDTO getTripById(Long id) {
        return mapper
                .mapToResponse(tripRepository.findById(id).orElseThrow(() ->
                        new TripNotFoundException("Trip not found with id: " + id)));
    }

    @Override
    public Trip updateTrip(Long id, TripRequestDTO dto) {
        Trip trip = tripRepository.findById(id).orElseThrow(() ->
                new TripNotFoundException("Trip not found with id: " + id));
        BeanUtils.copyProperties(dto, trip);
        validator.validateTripRequest(trip);
        tripRepository.save(trip);
        return trip;
    }
}
