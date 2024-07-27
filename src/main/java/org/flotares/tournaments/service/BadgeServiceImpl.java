package org.flotares.tournaments.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.flotares.tournaments.model.Badge;
import org.flotares.tournaments.repository.BadgeRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BadgeServiceImpl implements BadgeService {
    @Inject
    BadgeRepository badgeRepository;

    @Override
    public List<Badge> getAll() {
        return List.of();
    }

    @Override
    public Optional<Badge> getById(long id) {
        return Optional.empty();
    }

    @Override
    public Badge create(Badge badge) {
        badgeRepository.persist(badge);
        badgeRepository.flush();
        return badge;
    }

    @Override
    public Badge update(long id, Badge badge) {
        return null;
    }

    @Override
    public void delete(Badge badge) {
        badgeRepository.delete(badge);
    }
}
