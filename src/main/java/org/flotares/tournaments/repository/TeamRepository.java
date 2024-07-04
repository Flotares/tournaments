package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.flotares.tournaments.model.Team;

import java.util.List;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {
    public List<Team> findByTournamentId(long id) {
        return getEntityManager()
                .createNativeQuery(
                        "select * from Team t " +
                                "join Tournament_Team tt on tt.teams_id = t.id " +
                                "where tt.Tournament_id = :id " +
                                "order by t.name", Team.class)
                .setParameter("id", id)
                .getResultList();
    }
}
