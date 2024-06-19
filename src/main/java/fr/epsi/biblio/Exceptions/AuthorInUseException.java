package fr.epsi.biblio.Exceptions;

public class AuthorInUseException extends RuntimeException {
    public AuthorInUseException(String message) {
        super(message);
    }
}