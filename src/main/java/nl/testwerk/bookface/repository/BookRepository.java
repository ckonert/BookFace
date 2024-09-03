package nl.testwerk.bookface.repository;

import nl.testwerk.bookface.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
