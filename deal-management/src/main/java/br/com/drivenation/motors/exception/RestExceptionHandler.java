package br.com.drivenation.motors.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RestExceptionHandler implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return Response
                .status(exception.getStatus())
                .entity(errorResponse)
                .build();
    }
}
