package eubrunoo07.projects.fleetmanagement.trip_service.client;

import eubrunoo07.projects.fleetmanagement.trip_service.client.representation.VehicleRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "vehicles", url = "${fleet-management.trip-service.clients.vehicles.url}")
public interface VehicleClient {

    @GetMapping("/{id}")
    ResponseEntity<VehicleRepresentation> getVehicle(@PathVariable Long id);

    @PutMapping("/status/{id}/{status}")
    ResponseEntity<Void> updateStatus(@PathVariable Long id, @PathVariable String status);
}
