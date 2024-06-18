package fr.epsi.biblio.repository;

import fr.epsi.biblio.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
