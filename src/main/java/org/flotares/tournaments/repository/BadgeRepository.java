package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flotares.tournaments.model.Badge;

@ApplicationScoped
public class BadgeRepository implements PanacheRepository<Badge> {
}
