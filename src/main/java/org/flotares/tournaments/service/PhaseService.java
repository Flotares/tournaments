package org.flotares.tournaments.service;

import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.model.PhaseType;
import org.flotares.tournaments.model.Tournament;

import java.util.List;
import java.util.Optional;

public interface PhaseService extends CrudService<Phase> {
    List<Phase> getAllForTournament(Tournament t, String name, PhaseType phaseType, Integer phaseOrder);
    Phase insertPhase(Phase p);
    Optional<Phase> getPhaseByName(String name);

}
