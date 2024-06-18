package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.entity.Genre;
import fr.epsi.biblio.repository.AuthorRepository;
import fr.epsi.biblio.repository.BookRepository;
import fr.epsi.biblio.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public List<Book> findByGenreId(Long genreId) {
        List<Book> books = bookRepository.findAll();
        List<Book> booksAnswer = new ArrayList<>();
        for (Book book : books){
            if(book.getGenres().stream().anyMatch(genre -> Objects.equals(genre.getGenreId(), genreId))){
                booksAnswer.add(book);
            }
        }
        return booksAnswer;
    }
    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setAuthor(updatedBook.getAuthor());
            book.setGenres(updatedBook.getGenres());
            book.setIsbn(updatedBook.getIsbn());
            book.setTitle(updatedBook.getTitle());
            book.setPublicationDate(updatedBook.getPublicationDate());
            return bookRepository.save(book);
        });
    }

    public Optional<Book> patchBook(Long id, Map<String, Object> updates) {
        return bookRepository.findById(id).map(book -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "isbn":
                        book.setIsbn((String) value);
                        break;
                    case "title":
                        book.setTitle((String) value);
                        break;
                    case "author":
                        Long authorId = ((Number) value).longValue();
                        authorRepository.findById(authorId).ifPresent(book::setAuthor);
                        break;
                    case "genres":
                        Set<Long> genreIds = (Set<Long>) value;
                        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));
                        book.setGenres(genres);
                        break;
                    case "publicationDate":
                        book.setPublicationDate((String) value);
                        break;
                }
            });
            return bookRepository.save(book);
        });
    }

    public Book createBook(Book book) {
        return (bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
