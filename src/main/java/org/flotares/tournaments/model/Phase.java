package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated
    @Column(name = "phase_type")
    private PhaseType phaseType;
    @Column(unique = true)
    private String name;
    @Column(name = "phase_order")
    private int phaseOrder;
    @ManyToOne
    private Tournament tournament;
}
