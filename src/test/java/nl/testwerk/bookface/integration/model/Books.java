package nl.testwerk.bookface.integration.model;

import com.fasterxml.jackson.annotation.JsonValue;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import nl.testwerk.bookface.model.Book;

import java.util.List;

@Builder
@Data
public class Books {

    private final List<Book> books;

    @JsonCreator
    public Books(List<Book> books) {
        this.books = books;
    }

    @JsonValue
    public List<Book> getBooks() {
        return books;
    }
}