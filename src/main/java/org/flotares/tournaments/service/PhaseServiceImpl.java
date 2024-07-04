package org.flotares.tournaments.service;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.model.PhaseType;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.repository.PhaseRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PhaseServiceImpl implements PhaseService {
    @Inject
    PhaseRepository phaseRepository;

    @Override
    public List<Phase> getAll() {
        return phaseRepository.listAll(Sort.by("phaseOrder"));
    }

    @Override
    public List<Phase> getAllForTournament(Tournament t, String name, PhaseType phaseType, Integer phaseOrder) {
        return phaseRepository.getFilteredForTournament(t, name, phaseType, phaseOrder);
    }

    @Override
    public Phase insertPhase(Phase p) {
        phaseRepository.incrementIndexes(p.getPhaseOrder());
        Phase rp = this.create(p);
        return rp;
    }

    @Override
    public Optional<Phase> getPhaseByName(String name) {
        return phaseRepository.find("name", name).stream().findFirst();
    }

    @Override
    public Optional<Phase> getById(long id) {
        return phaseRepository.findByIdOptional(id);
    }

    @Override
    public Phase create(Phase p) {
        phaseRepository.persist(p);
        phaseRepository.flush();
        return p;
    }

    @Override
    public Phase update(long id, Phase p) {
        p.setId(id);
        Panache.getEntityManager().merge(p);
        return p;
    }

    @Override
    public void delete(Phase p) {
        phaseRepository.delete(p);
    }
}
