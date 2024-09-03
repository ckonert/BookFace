package nl.testwerk.bookface.controller;

import nl.testwerk.bookface.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
