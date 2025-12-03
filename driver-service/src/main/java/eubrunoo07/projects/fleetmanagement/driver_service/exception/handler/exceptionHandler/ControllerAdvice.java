package eubrunoo07.projects.fleetmanagement.driver_service.exception.handler.exceptionHandler;

import eubrunoo07.projects.fleetmanagement.driver_service.exception.DriverNotFoundException;
import eubrunoo07.projects.fleetmanagement.driver_service.exception.DriverValidationException;
import eubrunoo07.projects.fleetmanagement.driver_service.exception.handler.ApiErrors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return new ApiErrors(e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
    }

    @ExceptionHandler(DriverValidationException.class)
    public ApiErrors driverValidationExceptionHandler(DriverValidationException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ApiErrors driverNotFoundExceptionHandler(DriverNotFoundException e){
        return new ApiErrors(e.getMessage());
    }

}
