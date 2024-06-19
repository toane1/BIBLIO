package fr.epsi.biblio.Exceptions;

public class GenreInUseException extends RuntimeException {
    public GenreInUseException(String message) {
        super(message);
    }
}