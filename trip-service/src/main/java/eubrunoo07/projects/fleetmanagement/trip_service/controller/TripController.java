package eubrunoo07.projects.fleetmanagement.trip_service.controller;

import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripRequestDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.dto.TripResponseDTO;
import eubrunoo07.projects.fleetmanagement.trip_service.mapper.TripMapper;
import eubrunoo07.projects.fleetmanagement.trip_service.model.Trip;
import eubrunoo07.projects.fleetmanagement.trip_service.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fleet/management/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private TripMapper mapper;

    @PostMapping
    public ResponseEntity<TripResponseDTO> initTrip(@RequestBody@Valid TripRequestDTO dto){
        Trip trip = mapper.mapToEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToResponse(tripService.initTrip(trip)));
    }
    @PostMapping("/finish/{id}")
    public ResponseEntity<Void> finishTrip(@PathVariable Long id){
        tripService.finishTrip(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/")
    public ResponseEntity<List<TripResponseDTO>> getTrips(){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.getAllTrips());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TripResponseDTO> getTripById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TripResponseDTO> updateTrip(@PathVariable Long id, @RequestBody@Valid TripRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapToResponse(tripService.updateTrip(id, dto)));
    }
}
