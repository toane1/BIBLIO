package fr.epsi.biblio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users", schema="biblio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String lastname;
    private String firstname;
    private String password;
    private String email;
    private String phone;

    @OneToMany
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    private Set<Book> books = new HashSet<>();
}
