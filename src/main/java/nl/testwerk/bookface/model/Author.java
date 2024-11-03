package nl.testwerk.bookface.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Author {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String lastname;
        String firstname;
        String imageUrl;
}
