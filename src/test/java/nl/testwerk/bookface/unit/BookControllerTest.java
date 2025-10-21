package nl.testwerk.bookface.unit;

import nl.testwerk.bookface.controller.BookController;
import nl.testwerk.bookface.exception.BookNotFoundException;
import nl.testwerk.bookface.model.Author;
import nl.testwerk.bookface.model.Book;
import nl.testwerk.bookface.repository.AuthorRepository;
import nl.testwerk.bookface.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookRepository bookRepository;

    @MockitoBean
    private AuthorRepository authorRepository;

    @MockitoBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockitoBean
    private RestTemplate restTemplate;

    @Test
    void getAllBooks_ShouldReturnEmptyList() throws Exception {
        Mockito.when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllBooks_ShouldReturnBooksList() throws Exception {
        Author author = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Book book = Book.builder()
                .id(1L)
                .title("Harry Potter and the Philosopher's Stone")
                .isbn13("9780747532699")
                .authorId(1L)
                .imageUrl("https://example.com/harrypotter.jpg")
                .build();

        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Harry Potter and the Philosopher's Stone"))
                .andExpect(jsonPath("$[0].isbn13").value("9780747532699"));
    }

    @Test
    void getBook_ShouldReturnBook() throws Exception {
        Author author = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Book book = Book.builder()
                .id(1L)
                .title("Harry Potter and the Philosopher's Stone")
                .isbn13("9780747532699")
                .authorId(1L)
                .imageUrl("https://example.com/harrypotter.jpg")
                .build();

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Harry Potter and the Philosopher's Stone"))
                .andExpect(jsonPath("$.isbn13").value("9780747532699"));
    }

    @Test
    void getBook_ShouldReturnNotFound() throws Exception {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBook_ShouldReturnErrorIfAuthorNotFound() throws Exception {
        Mockito.when(authorRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(post("/books/")
                        .contentType("application/json")
                        .content("{\"title\": \"Harry Potter and the Philosopher's Stone\", \"isbn13\": \"9780747532699\", \"authorId\": 1, \"imageUrl\": \"https://example.com/harrypotter.jpg\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Author not found with ID: 1"));
    }

    @Test
    void createBook_ShouldCreateAndReturnBook() throws Exception {
        Book book = Book.builder()
                .id(1L)
                .title("Harry Potter and the Philosopher's Stone")
                .isbn13("9780747532699")
                .authorId(1L)
                .imageUrl("https://example.com/harrypotter.jpg")
                .build();

        Mockito.when(authorRepository.existsById(1L)).thenReturn(true);
        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books/")
                        .contentType("application/json")
                        .content("{\"title\": \"Harry Potter and the Philosopher's Stone\", \"isbn13\": \"9780747532699\", \"authorId\": 1, \"imageUrl\": \"https://example.com/harrypotter.jpg\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Harry Potter and the Philosopher's Stone"))
                .andExpect(jsonPath("$.isbn13").value("9780747532699"));
    }

    @Test
    void deleteBook_ShouldDeleteBook() throws Exception {
        Mockito.when(bookRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().is2xxSuccessful());

        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteBook_ShouldReturnNotFound() throws Exception {
        Mockito.when(bookRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/books/1"))
                .andExpect(result -> assertInstanceOf(BookNotFoundException.class, result.getResolvedException()));
    }
}
