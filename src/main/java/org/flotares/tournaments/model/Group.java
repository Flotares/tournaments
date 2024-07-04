package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TGroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Phase phase;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Team> teams;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Match> matches;
}
