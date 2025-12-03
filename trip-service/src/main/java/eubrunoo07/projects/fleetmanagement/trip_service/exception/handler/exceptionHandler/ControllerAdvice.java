package eubrunoo07.projects.fleetmanagement.trip_service.exception.handler.exceptionHandler;

import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripNotFoundException;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.TripValidationException;
import eubrunoo07.projects.fleetmanagement.trip_service.exception.handler.ApiErrors;
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

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors tripNotFoundExceptionHandler(TripNotFoundException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(TripValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors tripValidationExceptionHandler(TripValidationException e){
        return new ApiErrors(e.getMessage());
    }

}
