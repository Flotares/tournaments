package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Club club;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Player> players;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Coach> coaches;
    @ManyToOne
    private Pot pot;
}
