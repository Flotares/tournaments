package org.flotares.tournaments.service;

import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flotares.tournaments.model.Pot;
import org.flotares.tournaments.model.Team;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.repository.PotRepository;
import org.flotares.tournaments.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PotServiceImpl implements PotService {
    @Inject
    PotRepository potRepository;
    @Inject
    TeamService teamService;


    @Override
    public List<Pot> getAllForTournament(Tournament t) {
        return potRepository.find("tournament", t).list();
    }

    @Override
    public Team addTeamToPot(Pot pot, Team team) {
        team.setPot(pot);
        Team rt = teamService.update(team.getId(), team);
        return rt;
    }

    @Override
    public Team removeTeamFromPot(Team team) {
        team.setPot(null);
        Team rt = teamService.update(team.getId(), team);
        return rt;
    }

    @Override
    public List<Pot> getAll() {
        return potRepository.listAll();
    }


    @Override
    public Optional<Pot> getById(long id) {
        return potRepository.findByIdOptional(id);
    }

    @Override
    public Pot create(Pot pot) {
        potRepository.persist(pot);
        potRepository.flush();
        return pot;
    }

    @Override
    public Pot update(long id, Pot pot) {
        pot.setId(id);
        Panache.getEntityManager().merge(pot);
        return null;
    }

    @Override
    public void delete(Pot pot) {
        potRepository.delete(pot);
    }

}
