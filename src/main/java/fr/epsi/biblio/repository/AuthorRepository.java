package fr.epsi.biblio.repository;

import fr.epsi.biblio.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByAuthorId(Long authorId);
}
