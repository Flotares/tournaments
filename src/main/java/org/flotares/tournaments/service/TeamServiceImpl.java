package org.flotares.tournaments.service;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TeamServiceImpl implements TeamService {
    @Inject
    TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return teamRepository.listAll(Sort.by("name"));
    }

    @Override
    public Optional<Team> getById(long id) {
        return teamRepository.findByIdOptional(id);
    }

    @Override
    public Team create(Team t) {
        teamRepository.persist(t);
        teamRepository.flush();
        return t;
    }

    @Override
    public Team update(long id, Team t) {
        t.setId(id);
        Panache.getEntityManager().merge(t);
        return t;
    }

    @Override
    public void delete(Team t) {
        teamRepository.delete(t);
    }

    @Override
    public List<Team> getAllForTournament(long id) {
        return teamRepository.findByTournamentId(id);
    }

    @Override
    public Team getTeamByName(String name) {
        return teamRepository.find("name", name).firstResult();
    }
}
