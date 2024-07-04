package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flotares.tournaments.model.Club;

@ApplicationScoped
public class ClubRepository implements PanacheRepository<Club> {
}
