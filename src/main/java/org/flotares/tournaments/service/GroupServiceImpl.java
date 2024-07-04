package org.flotares.tournaments.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flotares.tournaments.model.Group;
import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GroupServiceImpl implements GroupService {

    @Inject
    GroupRepository groupRepository;

    @Override
    public List<Group> getAll() {
        return List.of();
    }

    @Override
    public Optional<Group> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Group create(Group group) {
        groupRepository.persist(group);
        groupRepository.flush();
        return group;
    }

    @Override
    public Group update(long id, Group group) {
        return null;
    }

    @Override
    public void delete(Group group) {

    }

    @Override
    public List<Group> getAllForPhase(Phase phase) {
        return groupRepository.find("phase", phase).list();
    }
}
