package org.flotares.tournaments.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Pot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    @JsonbTransient
    private Tournament tournament;
    @OneToMany(mappedBy = "pot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Team> teams;

    public void addTeam(Team t){
        teams.add(t);
        t.setPot(this);
    }

    public void removeTeam(Team t){
        teams.remove(t);
        t.setPot(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tournament);
    }
}
