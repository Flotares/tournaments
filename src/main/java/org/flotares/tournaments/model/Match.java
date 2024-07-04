package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "FootballMatch")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;
}
