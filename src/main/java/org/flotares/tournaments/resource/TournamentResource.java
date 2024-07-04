package org.flotares.tournaments.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.model.TournamentStatus;
import org.flotares.tournaments.service.TeamService;
import org.flotares.tournaments.service.TournamentService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Path("tournaments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Tournament Resource", description = "Endpoint for tournaments")
public class TournamentResource {
    private static final Logger LOGGER = Logger.getLogger(TournamentResource.class.getName());

    @Inject
    TournamentService tournamentService;


    @GET
    @Operation(summary = "Get all tournaments", description = "Returns all tournaments")
    public List<Tournament> getAllTournaments() {
        LOGGER.info("getAllTournaments");
        return tournamentService.getAll();
    }

    @GET
    @Path("latest")
    public Tournament getLatestTournament() {
        LOGGER.info("getLatestTournament");
        return tournamentService.getLatestTournament();
    }

    @GET
    @Path("status/{status}")
    public List<Tournament> getTournamentsByStatus(@PathParam("status") TournamentStatus status) {
        LOGGER.info(String.format("getTournamentsByStatus{%s}", status.toString()));
        return tournamentService.getTournamentsByStatus(status);
    }

    @GET
    @Path("{id}")
    public Optional<Tournament> getTournamentById(@PathParam("id") long id) {
        LOGGER.info(String.format("getTournamentById{%d}", id));
        return tournamentService.getById(id);
    }

    @POST
    public Tournament createTournament(Tournament t) {
        LOGGER.info(String.format("createTournament{%s}", t.toString()));

        Tournament rt = tournamentService.create(t);
        return rt;
    }

    @PUT
    @Path("{id}")
    public Response updateTournament(@PathParam("id") long id, Tournament t) {
        LOGGER.info(String.format("updateTournament{%d, %s}", id, t.toString()));

        Optional<Tournament> toUpdate = tournamentService.getById(id);
        System.out.println(toUpdate.isEmpty());
        if (toUpdate.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Tournament rt = tournamentService.update(id, t);

        return Response.ok(rt).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTournament(@PathParam("id") long id) {
        LOGGER.info(String.format("updateTournament{%d}", id));

        Optional<Tournament> toDelete = tournamentService.getById(id);

        if (toDelete.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        tournamentService.delete(toDelete.get());
        return Response.ok().build();
    }
}
