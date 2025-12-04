package eubrunoo07.projects.fleetmanagement.trip_service.service.impl;

import eubrunoo07.projects.fleetmanagement.trip_service.client.DriverClient;
import eubrunoo07.projects.fleetmanagement.trip_service.client.VehicleClient;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripRequestDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripResponseDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.enums.TripStatus;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripNotFoundException;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripValidationException;
import eubrunoo07.projects.fleetmanagement.trip_service.mapper.TripMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.TripCanceledPublisher;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.TripFinishedPublisher;
import eubrunoo07.projects.fleetmanagement.trip_service.publisher.TripStartedPublisher;
import eubrunoo07.projects.fleetmanagement.trip_service.repository.TripRepository;
import eubrunoo07.projects.fleetmanagement.trip_service.service.TripService;
import eubrunoo07.projects.fleetmanagement.trip_service.validator.TripValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Autowired
    private TripStartedPublisher startedPublisher;
    @Autowired
    private TripFinishedPublisher finishedPublisher;
    @Autowired
    private TripCanceledPublisher canceledPublisher;

    @Override
    public Trip initTrip(Trip trip) {
        validator.validateTripRequest(trip);
        var tripSaved = tripRepository.save(trip);
        startedPublisher.publish(tripSaved);
        return tripSaved;
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

    @Override
    public void finishTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() ->
                new TripNotFoundException("Trip not found with id: " + id));
        if(!trip.getStatus().equals(TripStatus.IN_PROGRESS)){
            throw new TripValidationException("To finish a trip must be in progress");
        }
        trip.setStatus(TripStatus.COMPLETED);
        trip.setEndDateTime(LocalDateTime.now());
        tripRepository.save(trip);
        finishedPublisher.publish(trip);
    }

    @Override
    public void cancelTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() ->
                new TripNotFoundException("Trip not found with id: " + id));
        if(!trip.getStatus().equals(TripStatus.IN_PROGRESS)){
            throw new TripValidationException("To cancel a trip must be in progress");
        }
        trip.setStatus(TripStatus.CANCELED);
        tripRepository.save(trip);
        canceledPublisher.publish(trip);
    }
}
