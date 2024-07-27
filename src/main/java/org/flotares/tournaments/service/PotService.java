package org.flotares.tournaments.service;

import org.flotares.tournaments.model.Pot;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;

import java.util.List;

public interface PotService extends CrudService<Pot> {
    List<Pot> getAllForTournament(Tournament t);
    Pot addTeamToPot(Pot pot, Team team);
    Pot removeTeamFromPot(Pot pot, Team team);
}
