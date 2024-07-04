package org.flotares.tournaments.service;

import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flotares.tournaments.model.Club;
import org.flotares.tournaments.repository.ClubRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClubServiceImpl implements ClubService {
    @Inject
    ClubRepository clubRepository;

    @Override
    public List<Club> getAll() {
        return clubRepository.listAll();
    }

    @Override
    public Optional<Club> getById(long id) {
        return clubRepository.findByIdOptional(id);
    }

    @Override
    public Club create(Club c) {
        clubRepository.persist(c);
        clubRepository.flush();
        return c;
    }

    @Override
    public Club update(long id, Club c) {
        c.setId(id);
        Panache.getEntityManager().merge(c);
        return c;
    }

    @Override
    public void delete(Club c) {
        clubRepository.delete(c);
    }
}
