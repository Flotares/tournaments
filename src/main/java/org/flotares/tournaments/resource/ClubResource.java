package org.flotares.tournaments.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.model.Club;
import org.flotares.tournaments.service.ClubService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;

@Path("clubs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Club Resource", description = "Endpoint for clubs")
public class ClubResource {
    private static final Logger LOGGER = Logger.getLogger(ClubResource.class);

    @Inject
    ClubService clubService;

    @GET
    public List<Club> getAllClubs() {
        return clubService.getAll();
    }

    @GET
    @Path("{id}")
    public Optional<Club> getClubById(@PathParam("id") long id) {
        return clubService.getById(id);
    }

    @POST
    public Club createClub(Club c) {
        Club rc = clubService.create(c);
        return rc;
    }

    @PUT
    @Path("{id}")
    public Response updateClub(@PathParam("id") long id, Club c) {
        Optional<Club> toUpdate = clubService.getById(id);

        if (toUpdate.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Club rc = clubService.update(id, c);

        return Response.ok(rc).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteClub(@PathParam("id") long id) {
        Optional<Club> toDelete = clubService.getById(id);

        if (toDelete.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        clubService.delete(toDelete.get());
        return Response.ok().build();
    }
}
