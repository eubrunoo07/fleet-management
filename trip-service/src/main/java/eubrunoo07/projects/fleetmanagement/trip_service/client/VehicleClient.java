package eubrunoo07.projects.fleetmanagement.trip_service.client;

import eubrunoo07.projects.fleetmanagement.trip_service.client.representation.VehicleRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicles", url = "${fleet-management.trip-service.clients.vehicles.url}")
public interface VehicleClient {

    @GetMapping("/{id}")
    public ResponseEntity<VehicleRepresentation> getVehicle(@PathVariable Long id);
}
