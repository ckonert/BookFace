package nl.testwerk.bookface.controller;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Author not found with ID: " + id);
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "BookNotFoundException{" +
                "message='" + getMessage() + '\'' +
                '}';
    }
}
