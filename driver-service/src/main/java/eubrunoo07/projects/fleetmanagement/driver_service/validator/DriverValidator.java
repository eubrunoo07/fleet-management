package eubrunoo07.projects.fleetmanagement.driver_service.validator;

import eubrunoo07.projects.fleetmanagement.driver_service.exception.DriverValidationException;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import eubrunoo07.projects.fleetmanagement.driver_service.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverValidator {

    @Autowired
    private DriverRepository driverRepository;

    public void createDriverRequestValidate(Driver driver){
        if(driverRepository.existsByCpf(driver.getCpf())){
            throw new DriverValidationException("This CPF is already registered");
        }
        if(driverRepository.existsByCnhNumber(driver.getCnhNumber())){
            throw new DriverValidationException("This CNH Number is already registered");
        }
    }

}
