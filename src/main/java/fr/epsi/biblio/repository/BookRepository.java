package fr.epsi.biblio.repository;


import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BookRepository extends JpaRepository<Book, Long> {
}
