package eubrunoo07.projects.fleetmanagement.trip_service.client;

import eubrunoo07.projects.fleetmanagement.trip_service.client.representation.DriverRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "drivers", url = "${fleet-management.trip-service.clients.drivers.url}")
public interface DriverClient {
    @GetMapping("/{id}")
    ResponseEntity<DriverRepresentation> getDriverById(@PathVariable Long id);

}
