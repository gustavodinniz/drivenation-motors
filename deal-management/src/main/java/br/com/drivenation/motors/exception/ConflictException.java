package br.com.drivenation.motors.exception;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestResponse;

@Getter
public class ConflictException extends RestException {

    public ConflictException(String message) {
        super(RestResponse.Status.CONFLICT, message);
    }

    public ConflictException(String message, Throwable cause) {
        super(RestResponse.Status.CONFLICT, message, cause);
    }
}
