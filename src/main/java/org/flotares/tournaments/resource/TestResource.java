package org.flotares.tournaments.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.flotares.tournaments.exception.ServiceException;

@Path("test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class TestResource {
    @GET
    public Response test(){
        throw new ServiceException(Response.Status.NOT_FOUND, "User not found");
    }
}
