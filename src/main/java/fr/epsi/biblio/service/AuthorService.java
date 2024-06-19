package fr.epsi.biblio.service;


import fr.epsi.biblio.Exceptions.AuthorInUseException;
import fr.epsi.biblio.entity.Author;
import fr.epsi.biblio.entity.Book;
import fr.epsi.biblio.repository.AuthorRepository;
import fr.epsi.biblio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Optional<Author> updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setFirstName(updatedAuthor.getFirstName());
            author.setLastName(updatedAuthor.getLastName());
            return authorRepository.save(author);
        });
    }

    public Optional<Author> patchAuthor(Long id, Map<String, Object> updates) {
        return authorRepository.findById(id).map(author -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "lastName":
                        author.setLastName((String) value);
                        break;
                    case "firstName":
                        author.setFirstName((String) value);
                        break;
                }
            });
            return authorRepository.save(author);
        });
    }
    public void deleteAuthor(Long id) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books){
            if(Objects.equals(book.getAuthor().getAuthorId(), id)){
                throw new AuthorInUseException("L'auteur " + book.getAuthor().getLastName()
                        + " " + book.getAuthor().getFirstName() + " ne peut être supprimé car il est référencé dans au moins 1 livre ("+
                        book.getTitle()+")");
            }
        }
        authorRepository.deleteById(id);
    }
}