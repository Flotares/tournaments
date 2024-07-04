package org.flotares.tournaments.resource;

import com.mysql.cj.log.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.flotares.tournaments.model.Pot;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.service.PotService;
import org.flotares.tournaments.service.TeamService;
import org.flotares.tournaments.service.TournamentService;

import java.util.Optional;
import java.util.logging.Logger;

@Path("tournaments/{id}/pots")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
@Tag(name = "Pot Resource", description = "Endpoint for pots")
public class PotResource {
    @PathParam("id")
    private long tId;

    @Inject
    TournamentService tournamentService;
    @Inject
    PotService potService;
    @Inject
    TeamService teamService;

    private static final Logger LOGGER = Logger.getLogger(PotResource.class.getName());

    @GET
    public Response getAllPots(){
        LOGGER.info("getAllPots");
        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(potService.getAllForTournament(tournament.get())).build();
    }

    @POST
    public Response createPot(Pot pot){
        LOGGER.info(String.format("createPot{%s}", pot.toString()));

        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        pot.setTournament(tournament.get());
        Pot rp = potService.create(pot);

        return Response.ok(rp).build();
    }

    @POST
    @Path("{id}/addTeam")
    public Response addTeamToPot(@PathParam("id") long pId, Team team){
        LOGGER.info(String.format("addTeamToPot{%s}", team.toString()));

        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Pot> pot = potService.getById(pId);

        if(pot.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(teamService.getById(team.getId()).isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Team rt = potService.addTeamToPot(pot.get(), team);

        return Response.ok(rt).build();
    }

    @POST
    @Path("{id}/removeTeam")
    public Response removeTeamFromPot(@PathParam("id") long pId, Team team){
        LOGGER.info(String.format("addTeamToPot{%s}", team.toString()));

        Optional<Tournament> tournament = tournamentService.getById(tId);

        if(tournament.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Pot> pot = potService.getById(pId);

        if(pot.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(teamService.getById(team.getId()).isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Team rt = potService.removeTeamFromPot(team);

        return Response.ok(rt).build();
    }
}
