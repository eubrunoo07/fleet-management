package eubrunoo07.projects.fleetmanagement.trip_service.exception.handler;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String error){
        this.errors = Collections.singletonList(error);
    }
}
