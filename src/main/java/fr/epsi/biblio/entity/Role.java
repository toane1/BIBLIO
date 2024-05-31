package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles", schema="biblio")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // ADMIN, USER
}