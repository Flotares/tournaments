package org.flotares.tournaments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
