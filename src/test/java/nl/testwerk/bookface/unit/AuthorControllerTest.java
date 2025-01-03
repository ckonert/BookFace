package nl.testwerk.bookface.unit;

import nl.testwerk.bookface.controller.AuthorController;
import nl.testwerk.bookface.exception.AuthorNotFoundException;
import nl.testwerk.bookface.repository.AuthorRepository;
import nl.testwerk.bookface.model.Author;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getAllAuthors_ShouldReturnEmptyList() throws Exception {
        Mockito.when(authorRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/authors/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllAuthors_ShouldReturnAuthorsList() throws Exception {
        Author author = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Mockito.when(authorRepository.findAll()).thenReturn(List.of(author));

        mockMvc.perform(get("/authors/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("J.K."))
                .andExpect(jsonPath("$[0].lastname").value("Rowling"));
    }

    @Test
    void getAuthor_ShouldReturnAuthor() throws Exception {
        Author author = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("J.K."))
                .andExpect(jsonPath("$.lastname").value("Rowling"));
    }

    @Test
    void getAuthor_ShouldReturnNotFound() throws Exception {
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAuthor_ShouldCreateAndReturnAuthor() throws Exception {
        Author author = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/authors/")
                        .contentType("application/json")
                        .content("{\"firstname\": \"J.K.\", \"lastname\": \"Rowling\", \"imageUrl\": \"https://example.com/rowling.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("J.K."))
                .andExpect(jsonPath("$.lastname").value("Rowling"));
    }

    @Test
    void updateAuthor_ShouldUpdateAndReturnAuthor() throws Exception {
        Author existingAuthor = Author.builder()
                .id(1L)
                .firstname("J.K.")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling.jpg")
                .build();

        Author updatedAuthor = Author.builder()
                .id(1L)
                .firstname("Joanne")
                .lastname("Rowling")
                .imageUrl("https://example.com/rowling-updated.jpg")
                .build();

        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(updatedAuthor);

        mockMvc.perform(put("/authors/1")
                        .contentType("application/json")
                        .content("{\"firstname\": \"Joanne\", \"lastname\": \"Rowling\", \"imageUrl\": \"https://example.com/rowling-updated.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Joanne"))
                .andExpect(jsonPath("$.lastname").value("Rowling"));
    }

    @Test
    void deleteAuthor_ShouldDeleteAuthor() throws Exception {
        Mockito.when(authorRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().is2xxSuccessful());

        Mockito.verify(authorRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteAuthor_ShouldReturnNotFound() throws Exception {
        Mockito.when(authorRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/authors/1"))
                .andExpect(result -> assertInstanceOf(AuthorNotFoundException.class, result.getResolvedException()));

    }
}
