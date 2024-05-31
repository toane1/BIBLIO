package fr.epsi.biblio.repository;

import fr.epsi.biblio.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByGenreId(Long genreId);
}
