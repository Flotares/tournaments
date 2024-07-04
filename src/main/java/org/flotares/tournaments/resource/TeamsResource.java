package org.flotares.tournaments.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.service.TeamService;
import org.flotares.tournaments.service.TournamentService;

import java.util.Optional;
import java.util.logging.Logger;

@Path("tournaments/{id}/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Team Resource", description = "Endpoint for teams")
public class TeamsResource {
    @Inject
    TeamService teamService;
    @Inject
    TournamentService tournamentService;
    @PathParam("id")
    private long id;

    private static final Logger LOGGER = Logger.getLogger(TeamsResource.class.getName());

    @GET
    public Response getAllTeams(){
        LOGGER.info("getAllTeams");
        Optional<Tournament> tournament = tournamentService.getById(id);

        if (tournament.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(teamService.getAllForTournament(id)).build();
    }

    @POST
    public Response addTeamToTournament(Team team){
        LOGGER.info(String.format("addClubToTournament{%d, %s}", id, team.toString()));

        Optional<Tournament> t = tournamentService.getById(id);

        if(t.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Team rt = teamService.create(team);

        Tournament rto = tournamentService.addTeamToTournament(t.get(), rt);

        return Response.ok(team).build();
    }
}
