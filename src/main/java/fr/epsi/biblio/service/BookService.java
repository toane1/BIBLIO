package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.entity.Genre;
import fr.epsi.biblio.repository.BookRepository;
import fr.epsi.biblio.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserService userService;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public List<Book> findByGenreId(Long genreId) {
        Set<Genre> genres = (Set<Genre>) genreRepository.findByGenreId(genreId);
        return bookRepository.findBookByGenres(genres);
    }

    public Book createBook(Book book) {
        return (bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
