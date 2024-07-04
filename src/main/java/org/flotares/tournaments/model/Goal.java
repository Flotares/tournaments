package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Match match;
    @ManyToOne
    private Player scorer;
    @ManyToOne
    private Team team;
}
