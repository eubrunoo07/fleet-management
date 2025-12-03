package eubrunoo07.projects.fleetmanagement.vehicle_service.mapper;

import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleRequestDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.dto.VehicleResponseDTO;
import eubrunoo07.projects.fleetmanagement.vehicle_service.model.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleResponseDTO mapToResponse(Vehicle vehicle){
        return VehicleResponseDTO
                .builder()
                .id(vehicle.getId())
                .model(vehicle.getModel())
                .modelYear(vehicle.getModelYear())
                .status(String.valueOf(vehicle.getStatus()))
                .licensePlate(vehicle.getLicensePlate())
                .brand(vehicle.getBrand())
                .active(vehicle.getActive())
                .build();
    }

    public Vehicle mapToEntity(VehicleRequestDTO dto){
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(dto, vehicle);
        return vehicle;
    }

}
