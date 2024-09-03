package nl.testwerk.bookface.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
public class Author {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String lastname;
        String firstname;
        String imageUrl;
}