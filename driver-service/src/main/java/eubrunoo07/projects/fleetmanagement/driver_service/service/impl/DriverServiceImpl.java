package eubrunoo07.projects.fleetmanagement.driver_service.service.impl;

import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverRequestDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverResponseDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.CnhCategory;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.DriverStatus;
import eubrunoo07.projects.fleetmanagement.driver_service.exception.DriverNotFoundException;
import eubrunoo07.projects.fleetmanagement.driver_service.mapper.DriverMapper;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import eubrunoo07.projects.fleetmanagement.driver_service.repository.DriverRepository;
import eubrunoo07.projects.fleetmanagement.driver_service.service.DriverService;
import eubrunoo07.projects.fleetmanagement.driver_service.validator.DriverValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverValidator validator;
    @Autowired
    private DriverMapper mapper;

    @Override
    public Driver createDriver(Driver driver) {
        //Valida a request (se existe cpf ou número da cnh)
        validator.createDriverRequestValidate(driver);
        return driverRepository.save(driver);
    }

    @Override
    public List<DriverResponseDTO> getDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        List<DriverResponseDTO> response = new ArrayList<>();
        drivers.forEach(driver -> response.add(mapper.mapToResponse(driver)));
        return response;
    }

    @Override
    public DriverResponseDTO getDriverById(Long id) {
        return mapper
                .mapToResponse(driverRepository.findById(id).orElseThrow(() ->
                        new DriverNotFoundException("Driver not found with id: " + id)));
    }

    @Override
    public Driver updateDriver(DriverRequestDTO dto, Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException("Driver not found with id: " + id));
        driver.setFullName(dto.getFullName());
        driver.setCnhCategory(CnhCategory.valueOf(dto.getCnhCategory()));
        driver.setCnhNumber(dto.getCnhNumber());
        driver.setCnhExpiresDate(dto.getCnhExpiresDate());
        driver.setCpf(dto.getCpf());
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public void deleteDriver(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException("Driver not found with id: " + id));
        driver.setActive(false);
        driver.setStatus(DriverStatus.INACTIVE);
        driverRepository.save(driver);
    }

    @Override
    public void updateStatus(Long id, String status) {
        Driver driver = driverRepository.findById(id).orElseThrow(() ->
                new DriverNotFoundException("Driver not found with id: " + id));
        driver.setStatus(DriverStatus.valueOf(status));
        driverRepository.save(driver);
    }
}
