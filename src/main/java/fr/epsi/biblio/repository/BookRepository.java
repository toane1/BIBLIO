package fr.epsi.biblio.repository;


import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookId(Long bookId);

    List<Book> findBookByGenres(Set<Genre> genres);
}
