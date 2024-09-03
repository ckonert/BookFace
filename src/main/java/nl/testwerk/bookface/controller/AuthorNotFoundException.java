package nl.testwerk.bookface.controller;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super("Author not found with ID: " + id);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "AuthorNotFoundException{" +
                "message='" + getMessage() + '\'' +
                '}';
    }
}
