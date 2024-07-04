package org.flotares.tournaments.repository;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import org.flotares.tournaments.model.Phase;
import org.flotares.tournaments.model.PhaseType;
import org.flotares.tournaments.model.Tournament;

import java.util.List;

@ApplicationScoped
public class PhaseRepository implements PanacheRepository<Phase> {
    public void incrementIndexes(int phaseOrder) {
        update("update Phase p set p.phaseOrder = p.phaseOrder + 1 where p.phaseOrder >= ?1", phaseOrder);
    }

    public List<Phase> getFilteredForTournament(Tournament t, String name, PhaseType phaseType, Integer phaseOrder){
        StringBuilder queryBuilder = new StringBuilder("select p from Phase p where p.tournament = :t");

        if(name != null){
            queryBuilder.append(" and p.name = :name");
        }
        if(phaseType != null){
            queryBuilder.append(" and p.phaseType = :phaseType");
        }
        if(phaseOrder != null){
            queryBuilder.append(" and p.phaseOrder = :phaseOrder");
        }

        TypedQuery<Phase> query = Panache.getEntityManager().createQuery(queryBuilder.toString(), Phase.class);
        query.setParameter("t", t);

        if (name != null) {
            query.setParameter("name", name);
        }
        if (phaseType != null) {
            query.setParameter("phaseType", phaseType);
        }
        if (phaseOrder != null) {
            query.setParameter("phaseOrder", phaseOrder);
        }

        return query.getResultList();
    }
}
