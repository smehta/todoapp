package com.todo.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smehta on 7/30/14.
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {

        final Set<String> failureMessage = new HashSet<>();
        for (final ConstraintViolation violation : e.getConstraintViolations()) {
            failureMessage.add(violation.getMessage());
        }
        return Response.status(400).entity(failureMessage).build();
    }
}
