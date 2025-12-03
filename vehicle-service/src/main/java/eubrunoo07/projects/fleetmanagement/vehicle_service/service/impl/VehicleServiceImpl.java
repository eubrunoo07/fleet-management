package eubrunoo07.projects.fleetmanagement.vehicle_service.service.impl;

import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleRequestDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleResponseDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.enums.VehicleStatus;
import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.VehicleNotFoundException;
import eubrunoo07.projects.fleetmanagement.vehicle_service.mapper.VehicleMapper;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import eubrunoo07.projects.fleetmanagement.vehicle_service.repository.VehicleRepository;
import eubrunoo07.projects.fleetmanagement.vehicle_service.service.VehicleService;
import eubrunoo07.projects.fleetmanagement.vehicle_service.validator.VehicleValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleMapper mapper;
    @Autowired
    private VehicleValidator validator;

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        validator.validateVehicleRequest(vehicle);
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public List<VehicleResponseDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleResponseDTO> response = new ArrayList<>();
        vehicles.forEach(vehicle -> response.add(mapper.mapToResponse(vehicle)));
        return response;
    }

    @Override
    public VehicleResponseDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository
                .findById(id).orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with id: " + id));
        return mapper.mapToResponse(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long id, VehicleRequestDTO dto) {
        Vehicle vehicle = vehicleRepository
                .findById(id).orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with id: " + id));
        BeanUtils.copyProperties(dto, vehicle);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository
                .findById(id).orElseThrow(() ->
                        new VehicleNotFoundException("Vehicle not found with id: " + id));
        vehicle.setActive(false);
        vehicleRepository.save(vehicle);
    }
}
