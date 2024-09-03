package nl.testwerk.bookface.repository;

import nl.testwerk.bookface.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
