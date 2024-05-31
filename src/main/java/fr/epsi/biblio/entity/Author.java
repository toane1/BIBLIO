package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="authors", schema="biblio")
@Data

public class Author {
    /**
     * The id of the author.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;
    /**
     * The firstname of the author.
     */
    private String firstName;
    /**
     * The lastname of the author.
     */
    private String lastName;
}
