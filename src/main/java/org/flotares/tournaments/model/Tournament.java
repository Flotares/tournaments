package org.flotares.tournaments.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonbDateFormat("dd.MM.yyyy")
    @Column(name = "start_date")
    private LocalDate date;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Team> teams;
    @Enumerated
    private TournamentStatus status;

    public void addTeam(Team t){
        this.teams.add(t);
    }
}
