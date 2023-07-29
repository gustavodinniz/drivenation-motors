package br.com.drivenation.motors.exception;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestResponse;

@Getter
public class BadRequestException extends RestException {

    public BadRequestException(String message) {
        super(RestResponse.Status.BAD_REQUEST, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(RestResponse.Status.BAD_REQUEST, message, cause);
    }
}
