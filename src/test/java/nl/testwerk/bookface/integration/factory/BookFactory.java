package nl.testwerk.bookface.integration.factory;

import nl.testwerk.bookface.model.Book;

public class BookFactory {

    public static Book makeBook(final String title, final Long authorId, final String isbn13, final String imageUrl){
        return Book.builder()
                .title(title)
                .authorId(authorId)
                .isbn13(isbn13)
                .imageUrl(imageUrl)
                .build();
    }
}
