package fr.epsi.biblio.service;


import fr.epsi.biblio.entity.Genre;
import fr.epsi.biblio.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Optional<Genre> updateGenre(Long id, Genre updatedGenre) {
        return genreRepository.findById(id).map(genre -> {
            genre.setGenre(updatedGenre.getGenre());
            return genreRepository.save(genre);
        });
    }

    public Optional<Genre> patchGenre(Long id, Map<String, Object> updates) {
        return genreRepository.findById(id).map(genre -> {
            updates.forEach((key, value) -> {
                if ("genre".equals(key)) {
                    genre.setGenre((String) value);
                }
            });
            return genreRepository.save(genre);
        });
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
