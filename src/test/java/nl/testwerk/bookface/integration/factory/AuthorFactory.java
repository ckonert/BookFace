package nl.testwerk.bookface.integration.factory;

import nl.testwerk.bookface.model.Author;

public class AuthorFactory {

    public static Author makeAuthor(final String firstName, final String lastName) {
        return Author.builder()
                .firstname(firstName)
                .lastname(lastName)
                .build();
    }
}
