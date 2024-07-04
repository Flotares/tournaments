package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flotares.tournaments.model.Pot;

@ApplicationScoped
public class PotRepository implements PanacheRepository<Pot> {
}
