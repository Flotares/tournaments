package org.flotares.tournaments.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Club club;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Player> players;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Coach> coaches;
    @ManyToOne
    @JoinColumn(name = "pot_id")
    @JsonbTransient
    private Pot pot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && Objects.equals(club, team.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, club);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", club=" + club +
                ", players=" + players +
                ", coaches=" + coaches +
                '}';
    }
}
