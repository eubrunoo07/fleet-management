package eubrunoo07.projects.fleetmanagement.vehicle_service.exception.handler.exceptionHandler;

import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.VehicleNotFoundException;
import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.VehicleValidationException;
import eubrunoo07.projects.fleetmanagement.vehicle_service.exception.handler.ApiErrors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return new ApiErrors(e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors vehicleNotFoundExceptionHandler(VehicleNotFoundException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(VehicleValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors vehicleValidationExceptionHandler(VehicleValidationException e){
        return new ApiErrors(e.getMessage());
    }

}
