package br.com.drivenation.motors.exception;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestResponse;

@Getter
public class RestException extends RuntimeException {

    public final RestResponse.Status status;

    public RestException(RestResponse.Status status, String message) {
        super(message);
        this.status = status;
    }

    public RestException(RestResponse.Status status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
