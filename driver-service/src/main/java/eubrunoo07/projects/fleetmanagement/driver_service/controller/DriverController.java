package eubrunoo07.projects.fleetmanagement.driver_service.controller;

import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverRequestDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverResponseDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.CnhCategory;
import eubrunoo07.projects.fleetmanagement.driver_service.mapper.DriverMapper;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import eubrunoo07.projects.fleetmanagement.driver_service.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fleet/management/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper mapper;

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@RequestBody@Valid DriverRequestDTO dto){
        Driver driver = new Driver();
        BeanUtils.copyProperties(dto, driver);
        driver.setCnhCategory(CnhCategory.valueOf(dto.getCnhCategory()));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToResponse(driverService.createDriver(driver)));
    }
    @GetMapping("/")
    public ResponseEntity<List<DriverResponseDTO>> getDrivers(){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.getDrivers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getDriverById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(driverService.getDriverById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable Long id, @RequestBody@Valid DriverRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.mapToResponse(driverService.updateDriver(dto, id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id){
        driverService.deleteDriver(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
