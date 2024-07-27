package org.flotares.tournaments.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {
    @Override
    public Response toResponse(ServiceException e) {
        return Response.status(e.getStatus())
                .entity(new ServiceExceptionMessage(e.getStatus().getStatusCode(), e.getStatus().toString(), e.getMessage()))
                .build();
    }
}
