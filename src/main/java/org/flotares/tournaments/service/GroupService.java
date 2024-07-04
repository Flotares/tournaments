package org.flotares.tournaments.service;

import org.flotares.tournaments.model.Group;
import org.flotares.tournaments.model.Phase;

import java.util.List;

public interface GroupService extends CrudService<Group> {
    List<Group> getAllForPhase(Phase phase);
}
