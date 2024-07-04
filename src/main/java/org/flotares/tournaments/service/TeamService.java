package org.flotares.tournaments.service;

import org.flotares.tournaments.model.Team;

import java.util.List;

public interface TeamService extends CrudService<Team> {
    List<Team> getAllForTournament(long id);
    Team getTeamByName(String name);
}
