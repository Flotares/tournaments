package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.flotares.tournaments.model.Tournament;
import org.flotares.tournaments.model.TournamentStatus;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {
    public Tournament findLiveTournamentsOrderedByDateDesc(){
        return find("status", Sort.by("date").descending(), TournamentStatus.LIVE).firstResult();
    }
}
