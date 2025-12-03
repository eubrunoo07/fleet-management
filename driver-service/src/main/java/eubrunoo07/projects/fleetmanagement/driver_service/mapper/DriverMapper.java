package eubrunoo07.projects.fleetmanagement.driver_service.mapper;

import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverRequestDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.dto.DriverResponseDTO;
import eubrunoo07.projects.fleetmanagement.driver_service.enums.CnhCategory;
import eubrunoo07.projects.fleetmanagement.driver_service.model.Driver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    public DriverResponseDTO mapToResponse(Driver driver){
        return
                DriverResponseDTO
                        .builder()
                        .id(driver.getId())
                        .cpf(driver.getCpf())
                        .cnhExpiresDate(driver.getCnhExpiresDate())
                        .cnhCategory(String.valueOf(driver.getCnhCategory()))
                        .fullName(driver.getFullName())
                        .cnhNumber(driver.getCnhNumber())
                        .fullName(driver.getFullName())
                        .active(driver.getActive())
                        .status(String.valueOf(driver.getStatus()))
                        .build();
    }

    public Driver mapToEntity(DriverRequestDTO dto){
        Driver driver = new Driver();
        BeanUtils.copyProperties(driver, driver);
        driver.setCnhCategory(CnhCategory.valueOf(dto.getCnhCategory()));
        return driver;
    }
}
