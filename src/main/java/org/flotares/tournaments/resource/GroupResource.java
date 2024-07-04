package org.flotares.tournaments.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.model.Group;
import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.service.GroupService;
import org.flotares.tournaments.service.PhaseService;
import org.flotares.tournaments.service.TournamentService;

import java.util.Optional;
import java.util.logging.Logger;

@Path("tournaments/{tId}/phases/{pId}/groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Group Resource", description = "Endpoint for groups")
public class GroupResource {
    @PathParam("tId")
    private long tId;
    @PathParam("pId")
    private long pId;

    @Inject
    PhaseService phaseService;
    @Inject
    TournamentService tournamentService;
    @Inject
    GroupService groupService;

    private static final Logger LOGGER = Logger.getLogger(GroupResource.class.getName());

    @GET
    public Response getAllGroups(){
        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Phase> phase = phaseService.getById(pId);

        if(phase.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Phase not found").build();
        }


        return Response.ok(groupService.getAllForPhase(phase.get())).build();
    }

    @POST
    public Response createGroup(Group g){
        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Phase> phase = phaseService.getById(pId);

        if(phase.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Phase not found").build();
        }

        g.setPhase(phase.get());
        Group cg = groupService.create(g);
        return Response.ok(cg).build();
    }
}
