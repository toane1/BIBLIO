package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "borrows", schema="biblio")
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private boolean givenBack;
}
