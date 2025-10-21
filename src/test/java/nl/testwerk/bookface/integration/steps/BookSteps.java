package nl.testwerk.bookface.integration.steps;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import nl.testwerk.bookface.integration.factory.BookFactory;
import nl.testwerk.bookface.integration.model.Books;
import nl.testwerk.bookface.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@Slf4j
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookSteps {
    @Autowired
    private TestRestTemplate restTemplate;
    private Book book;

    private ResponseEntity<String> responseEntity;

    @Given("the book data is prepared")
    public void prepareBookData() {
        book = BookFactory.makeBook("The Hobbit", 1001L, "1234567890123", "https://www.memoriapress.com/wp-content/uploads/Hobbit-2.jpg");
    }

    @When("a POST request is made to create a book")
    public void postBookData() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> entity = new HttpEntity<>(book, headers);

        responseEntity = restTemplate.exchange("/books/", HttpMethod.POST, entity, String.class);
    }

    @Given("a GET request is made to the books endpoint")
    public void getBooksData() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<Book> entity = new HttpEntity<>(book, headers);

        responseEntity = restTemplate.exchange("/books/", HttpMethod.GET, entity, String.class);
    }

    @Then("the book is successfully added")
    public void verifyBookCreation() {
        var objectMapper = new ObjectMapper();
        try {
            var body = responseEntity.getBody();
            var book = objectMapper.readValue(body, Book.class);
            String prettyPrintJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
            log.info(prettyPrintJson);
            assertAll(
                    "Check the create body",
                    () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "Status is incorrect"),
                    () -> assertEquals(5000L, book.getId(), "ID is incorrect"),
                    () -> assertEquals("The Hobbit", book.getTitle(), "Title is incorrect"),
                    () -> assertEquals("1234567890123", book.getIsbn13(), "ISBN is incorrect"),
                    () -> assertEquals(1001L, book.getAuthorId(), "Author is incorrect"),
                    () -> assertEquals("https://www.memoriapress.com/wp-content/uploads/Hobbit-2.jpg", book.getImageUrl(), "ImageUrl is incorrect")
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the book data is returned")
    public void theBookDataIsReturned() {
        var objectMapper = new ObjectMapper();
        try {
            var body = responseEntity.getBody();
            var bookList = objectMapper.readValue(body, Books.class);
            String prettyPrintJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookList);
            log.info(prettyPrintJson);

            assertAll(
                    "Check the first book in the list of books",
                    () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "Status is incorrect"),
                    () -> assertEquals(2001L, bookList.getBooks().getFirst().getId(), "ID is incorrect"),
                    () -> assertEquals("A Game of Thrones", bookList.getBooks().getFirst().getTitle(), "Title is incorrect"),
                    () -> assertEquals("0553808044", bookList.getBooks().getFirst().getIsbn13(), "ISBN is incorrect"),
                    () -> assertEquals(1002L, bookList.getBooks().getFirst().getAuthorId(), "Author is incorrect"),
                    () -> assertNotNull(bookList.getBooks().getFirst().getImageUrl(), "ImageUrl is empty")
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
