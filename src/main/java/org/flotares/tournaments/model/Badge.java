package org.flotares.tournaments.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Column(length = 5_000_000)
    private byte[] data;
}
