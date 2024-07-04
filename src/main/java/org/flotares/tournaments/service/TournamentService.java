package org.flotares.tournaments.service;

import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.model.TournamentStatus;

import java.util.List;


public interface TournamentService extends CrudService<Tournament> {
    Tournament getLatestTournament();
    List<Tournament> getTournamentsByStatus(TournamentStatus status);
    Tournament addTeamToTournament(Tournament t, Team team);
}
