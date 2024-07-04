package org.flotares.tournaments.service;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.flotares.tournaments.model.Club;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.model.TournamentStatus;
import org.flotares.tournaments.repository.TournamentRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TournamentServiceImpl implements TournamentService {
    @Inject
    TournamentRepository tournamentRepository;

    @Override
    public List<Tournament> getAll() {
        return tournamentRepository.listAll();
    }

    @Override
    public Optional<Tournament> getById(long id) {
        return tournamentRepository.findByIdOptional(id);
    }

    @Override
    public Tournament create(Tournament tournament) {
        tournamentRepository.persist(tournament);
        tournamentRepository.flush();
        return tournament;
    }

    @Override
    public Tournament update(long id, Tournament t) {
        t.setId(id);
        Panache.getEntityManager().merge(t);
        return t;
    }

    @Override
    public void delete(Tournament tournament) {
        tournamentRepository.delete(tournament);
    }

    @Override
    public Tournament getLatestTournament() {
        return tournamentRepository.findLiveTournamentsOrderedByDateDesc();
    }

    @Override
    public List<Tournament> getTournamentsByStatus(TournamentStatus status) {
        return tournamentRepository.find("status", status).list();
    }

    @Override
    public Tournament addTeamToTournament(Tournament t, Team team) {
        t.addTeam(team);
        return t;
    }
}
