package br.com.drivenation.motors.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ErrorModel> errors = e.getConstraintViolations().stream()
                .map(violation -> new ErrorModel(extractCode(violation), extractMessage(violation))).toList();
        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }

    private String extractCode(ConstraintViolation<?> violation) {
        return violation.getMessageTemplate().replace("{", "").replace("}", "");
    }

    private String extractMessage(ConstraintViolation<?> violation) {
        return violation.getMessage().replace("{}", violation.getPropertyPath().toString().lastIndexOf(".") > 0 ?
                violation.getPropertyPath().toString().substring(violation.getPropertyPath().toString().lastIndexOf(".") + 1) :
                violation.getPropertyPath().toString());
    }
}
