package org.flotares.tournaments.exception;

import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private Response.Status status;

    public ServiceException(Response.Status status, String message) {
        super(message);
        this.status = status;
    }
}

