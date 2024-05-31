package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genres", schema="biblio")
@Data

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    private String genre;
}
