package nl.testwerk.bookface.controller;

import nl.testwerk.bookface.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
