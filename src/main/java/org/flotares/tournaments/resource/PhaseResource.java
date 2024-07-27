package org.flotares.tournaments.resource;

import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.exception.ServiceException;
import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.model.PhaseType;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.service.PhaseService;
import org.flotares.tournaments.service.TournamentService;

import java.util.Optional;
import java.util.logging.Logger;

@Path("tournaments/{id}/phases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Phase Resource", description = "Endpoint for phases")
public class PhaseResource {
    @PathParam("id")
    private long id;

    @Inject
    PhaseService phaseService;
    @Inject
    TournamentService tournamentService;

    private static final Logger LOGGER = Logger.getLogger(PhaseResource.class.getName());


    @GET
    public Response getAllPhases(@QueryParam("name") @Nullable String name,
                                 @QueryParam("phaseType") @Nullable PhaseType phaseType,
                                 @QueryParam("phaseOrder") @Nullable Integer phaseOrder) {
        LOGGER.info(String.format("getAllPhases{%s, &s, %d}", name, phaseType, phaseOrder));
        Optional<Tournament> tournament = tournamentService.getById(id);

        if (tournament.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Tournament not found");
        }

        return Response.ok(phaseService.getAllForTournament(tournament.get(), name, phaseType, phaseOrder)).build();
    }

    @POST
    public Response insertPhase(Phase p) {
        LOGGER.info(String.format("insertPhase{%s}", p.toString()));

        Optional<Tournament> tournament = tournamentService.getById(id);

        if (tournament.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Tournament not found");
        }

        p.setTournament(tournament.get());
        Phase rp = phaseService.insertPhase(p);

        return Response.ok(rp).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePhase(@PathParam("id") long id) {
        LOGGER.info(String.format("deletePhase{%d}", id));

        Optional<Phase> phase = phaseService.getById(id);

        if (phase.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Phase not found");
        }
        phaseService.delete(phase.get());

        return Response.ok().build();
    }

    @GET
    @Path("{id}/addTeamsToGroups")
    public Response addTeamsToGroups(@PathParam("id") long id){
        LOGGER.info(String.format("addTeamsToGroups{%d}", id));

        Optional<Tournament> tournament = tournamentService.getById(id);

        if (tournament.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Tournament not found");
        }

        Optional<Phase> phase = phaseService.getById(id);

        if (phase.isEmpty()) {
            throw new ServiceException(Response.Status.NOT_FOUND, "Phase not found");
        }

        return Response.ok().build();
    }
}
