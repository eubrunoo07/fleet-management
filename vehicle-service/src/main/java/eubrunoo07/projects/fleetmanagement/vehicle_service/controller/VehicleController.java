package eubrunoo07.projects.fleetmanagement.vehicle_service.controller;

import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleRequestDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleResponseDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.mapper.VehicleMapper;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import eubrunoo07.projects.fleetmanagement.vehicle_service.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fleet/management/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleMapper mapper;

    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(@RequestBody@Valid VehicleRequestDTO dto){
        Vehicle vehicle = mapper.mapToEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToResponse(vehicleService.createVehicle(vehicle)));
    }
    @GetMapping("/")
    public ResponseEntity<List<VehicleResponseDTO>> getVehicles(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicle(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicleById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable Long id, @RequestBody@Valid VehicleRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapToResponse(vehicleService.updateVehicle(id, dto)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
