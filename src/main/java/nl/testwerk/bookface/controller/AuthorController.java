package nl.testwerk.bookface.controller;

import nl.testwerk.bookface.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final AuthorRepository authorRepository;

    AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    Author author(@PathVariable Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PostMapping("/")
    Author createAuthor(@RequestBody Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @PutMapping("/{id}")
    Author updateAuthor(@RequestBody Author updatedAuthor, @PathVariable Long id) {
        return authorRepository.findById(id).map(author -> {
            author.setFirstname(updatedAuthor.getFirstname());
            author.setLastname(updatedAuthor.getLastname());
            author.setImageUrl(updatedAuthor.getImageUrl());
            return authorRepository.save(author);
        }).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteAuthor(@PathVariable Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }


    // Exception handler for AuthorNotFoundException
    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
