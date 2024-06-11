package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "books", schema="biblio")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String isbn;

    private String title;

    @ManyToOne
    private Author author;

    @ManyToMany
    @JoinTable(name="GEN_BOOK",
        joinColumns=@JoinColumn(name="id_book", referencedColumnName="bookId"),
        inverseJoinColumns=@JoinColumn(name="id_genre", referencedColumnName="genreId")
    )
    private Set<Genre> genres;

    private String publicationDate;

}
