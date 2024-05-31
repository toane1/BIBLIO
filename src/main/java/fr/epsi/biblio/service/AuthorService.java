package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Author;
import fr.epsi.biblio.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

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
        authorRepository.deleteById(id);
    }
}